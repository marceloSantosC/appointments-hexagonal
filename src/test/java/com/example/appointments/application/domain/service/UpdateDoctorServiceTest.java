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

import com.example.appointments.application.domain.exception.DoctorDoesNotExistsException;
import com.example.appointments.application.domain.model.Doctor;
import com.example.appointments.application.port.in.model.UpdateDoctorModel;
import com.example.appointments.application.port.out.DoctorPersistencePort;

@ExtendWith(MockitoExtension.class)
class UpdateDoctorServiceTest {

	@InjectMocks
	private UpdateDoctorService service;

	@Mock
	private DoctorPersistencePort doctorPersistencePort;

	@Test
	void should_update_doctor_info() {

		var id = 1L;
		var updateModel = new UpdateDoctorModel("Jhon J Doe", "11912341235", "11912341234", "jhonj@email.com");

		var doctor = new Doctor(id, "Jhon", "Generalist", "11912341234", null, "jhon@email.com", "CRM/SP 123456",
				"12345678909", LocalTime.MIN, LocalTime.MAX);
		doReturn(Optional.of(doctor)).when(doctorPersistencePort).loadById(doctor.getId());

		service.update(id, updateModel);

		verify(doctorPersistencePort).loadById(id);
		verify(doctorPersistencePort).update(doctor);
	}

	@Test
	void should_not_update_non_existing_doctor_info() {

		var id = 1L;
		var updateModel = new UpdateDoctorModel("Jhon J Doe", "11912341235", "11912341234", "jhonj@email.com");
		doReturn(Optional.empty()).when(doctorPersistencePort).loadById(anyLong());

		assertThrows(DoctorDoesNotExistsException.class, () -> service.update(id, updateModel));

		verify(doctorPersistencePort).loadById(id);
		verify(doctorPersistencePort, never()).update(any(Doctor.class));
	}

}