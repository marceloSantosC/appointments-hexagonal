package com.example.appointments.application.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.appointments.application.domain.exception.DoctorExistsException;
import com.example.appointments.application.domain.model.Doctor;
import com.example.appointments.application.port.out.DoctorPersistencePort;

@ExtendWith(MockitoExtension.class)
class RegisterDoctorServiceTest {

	@InjectMocks
	private RegisterDoctorService service;

	@Mock
	private DoctorPersistencePort doctorPersistencePort;

	@Test
	void should_register_doctor() {
		Doctor doctor = new Doctor(1L, "Jhon", "Generalist", "11912341234", null, "jhon@email.com", "CRM/SP 123456",
				"12345678909", LocalTime.MIN, LocalTime.MAX);
		doReturn(Optional.empty()).when(doctorPersistencePort).loadByDocument(doctor.getDocument());
		doReturn(1L).when(doctorPersistencePort).save(doctor);

		service.register(doctor);

		verify(doctorPersistencePort).loadByDocument(doctor.getDocument());
		verify(doctorPersistencePort).save(doctor);
	}

	@Test
	void should_not_register_existing_doctor() {
		Doctor doctor = new Doctor(1L, "Jhon", "Generalist", "11912341234", null, "jhon@email.com", "CRM/SP 123456",
				"12345678909", LocalTime.MIN, LocalTime.MAX);
		doReturn(Optional.of(doctor)).when(doctorPersistencePort).loadByDocument(doctor.getDocument());

		assertThrows(DoctorExistsException.class, () -> service.register(doctor));

		verify(doctorPersistencePort).loadByDocument(doctor.getDocument());
		verify(doctorPersistencePort, never()).save(doctor);
	}

}