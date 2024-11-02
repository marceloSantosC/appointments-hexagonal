package com.example.appointments.application.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.appointments.application.domain.exception.DoctorExistsException;
import com.example.appointments.application.domain.model.Doctor;
import com.example.appointments.application.port.out.LoadDoctorByDocumentPort;
import com.example.appointments.application.port.out.SaveDoctorPort;

@ExtendWith(MockitoExtension.class)
class RegisterDoctorServiceTest {

	@InjectMocks
	private RegisterDoctorService service;

	@Mock
	private SaveDoctorPort saveDoctor;

	@Mock
	private LoadDoctorByDocumentPort loadDoctorByDocument;

	@Test
	void should_register_doctor() {
		Doctor doctor = new Doctor(1L, "Jhon", "Generalist", "11912341234", null, "jhon@email.com", "CRM/SP 123456",
				"12345678909");
		doReturn(Optional.empty()).when(loadDoctorByDocument).load(doctor.getDocument());
		doReturn(1L).when(saveDoctor).save(doctor);

		service.register(doctor);

		verify(loadDoctorByDocument).load(doctor.getDocument());
		verify(saveDoctor).save(doctor);
	}

	@Test
	void should_not_register_existing_doctor() {
		Doctor doctor = new Doctor(1L, "Jhon", "Generalist", "11912341234", null, "jhon@email.com", "CRM/SP 123456",
				"12345678909");
		doReturn(Optional.of(doctor)).when(loadDoctorByDocument).load(doctor.getDocument());

		assertThrows(DoctorExistsException.class, () -> service.register(doctor));

		verify(loadDoctorByDocument).load(doctor.getDocument());
		verify(saveDoctor, never()).save(doctor);
	}

}