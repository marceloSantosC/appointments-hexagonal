package com.example.appointments.application.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.appointments.application.domain.exception.DoctorDoesNotExistsException;
import com.example.appointments.application.domain.model.Doctor;
import com.example.appointments.application.port.out.LoadDoctorByDocumentPort;
import com.example.appointments.application.port.out.LoadDoctorByIdPort;

@ExtendWith(MockitoExtension.class)
class GetDoctorInfoServiceTest {

	@InjectMocks
	private GetDoctorInfoService service;

	@Mock
	private LoadDoctorByDocumentPort loadDoctorByDocument;

	@Mock
	private LoadDoctorByIdPort loadDoctorById;

	@Test
	void should_get_doctor_info_by_document() {
		var document = "12345678909";
		var doctor = new Doctor(1L, "Jhon", "Generalist", "11912341234", null, "jhon@email.com", "CRM/SP 123456",
				document);
		doReturn(Optional.of(doctor)).when(loadDoctorByDocument).load(document);

		service.getInfo(null, document);

		verify(loadDoctorByDocument).load(document);
		verify(loadDoctorById, never()).load(anyLong());
	}

	@Test
	void should_get_doctor_info_by_id() {
		var id = 1L;

		var doctor = new Doctor(id, "Jhon", "Generalist", "11912341234", null, "jhon@email.com", "CRM/SP 123456",
				"12345678909");
		doReturn(Optional.of(doctor)).when(loadDoctorById).load(id);

		service.getInfo(id, null);

		verify(loadDoctorByDocument, never()).load(anyString());
		verify(loadDoctorById).load(id);
	}

	@Test
	void should_not_get_non_existing_doctor_info_by_document() {
		var document = "12345678909";
		doReturn(Optional.empty()).when(loadDoctorByDocument).load(document);

		assertThrows(DoctorDoesNotExistsException.class, () -> service.getInfo(null, document));

		verify(loadDoctorByDocument).load(document);
		verify(loadDoctorById, never()).load(anyLong());
	}

	@Test
	void should_not_get_non_existing_doctor_info_by_id() {
		var id = 1L;
		doReturn(Optional.empty()).when(loadDoctorById).load(id);

		assertThrows(DoctorDoesNotExistsException.class, () -> service.getInfo(id, null));

		verify(loadDoctorByDocument, never()).load(anyString());
		verify(loadDoctorById).load(anyLong());
	}

}