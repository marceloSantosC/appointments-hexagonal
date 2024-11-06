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
import com.example.appointments.application.port.out.DoctorPersistencePort;

@ExtendWith(MockitoExtension.class)
class GetDoctorInfoServiceTest {

	@InjectMocks
	private GetDoctorInfoService service;

	@Mock
	private DoctorPersistencePort doctorPersistencePort;

	@Test
	void should_get_doctor_info_by_document() {
		var document = "12345678909";
		var doctor = new Doctor(1L, "Jhon", "Generalist", "11912341234", null, "jhon@email.com", "CRM/SP 123456",
				document, LocalTime.MIN, LocalTime.MAX);
		doReturn(Optional.of(doctor)).when(doctorPersistencePort).loadByDocument(document);

		service.getInfo(null, document);

		verify(doctorPersistencePort).loadByDocument(document);
		verify(doctorPersistencePort, never()).loadById(anyLong());
	}

	@Test
	void should_get_doctor_info_by_id() {
		var id = 1L;

		var doctor = new Doctor(id, "Jhon", "Generalist", "11912341234", null, "jhon@email.com", "CRM/SP 123456",
				"12345678909", LocalTime.MIN, LocalTime.MAX);
		doReturn(Optional.of(doctor)).when(doctorPersistencePort).loadById(id);

		service.getInfo(id, null);

		verify(doctorPersistencePort, never()).loadByDocument(anyString());
		verify(doctorPersistencePort).loadById(id);
	}

	@Test
	void should_not_get_non_existing_doctor_info_by_document() {
		var document = "12345678909";
		doReturn(Optional.empty()).when(doctorPersistencePort).loadByDocument(document);

		assertThrows(DoctorDoesNotExistsException.class, () -> service.getInfo(null, document));

		verify(doctorPersistencePort).loadByDocument(document);
		verify(doctorPersistencePort, never()).loadById(anyLong());
	}

	@Test
	void should_not_get_non_existing_doctor_info_by_id() {
		var id = 1L;
		doReturn(Optional.empty()).when(doctorPersistencePort).loadById(id);

		assertThrows(DoctorDoesNotExistsException.class, () -> service.getInfo(id, null));

		verify(doctorPersistencePort, never()).loadByDocument(anyString());
		verify(doctorPersistencePort).loadById(anyLong());
	}

}