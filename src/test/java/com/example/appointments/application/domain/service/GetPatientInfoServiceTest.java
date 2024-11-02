package com.example.appointments.application.domain.service;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.appointments.application.domain.model.Patient;
import com.example.appointments.application.port.out.LoadPatientByDocumentPort;
import com.example.appointments.application.port.out.LoadPatientByIdPort;

@ExtendWith(MockitoExtension.class)
class GetPatientInfoServiceTest {

	@InjectMocks
	private GetPatientInfoService getPatientInfoService;

	@Mock
	private LoadPatientByIdPort loadPatientById;

	@Mock
	private LoadPatientByDocumentPort loadPatientByDocument;

	@Test
	void should_get_patient_info_by_id() {
		var id = 1L;
		var patient = new Patient("Jhon Doe", "12345678909", LocalDate.parse("2000-10-10"), "Lorem Ipsum",
				"11912341234", null, "test@email.com");
		patient.setId(id);
		doReturn(Optional.of(patient)).when(loadPatientById).load(patient.getId());

		getPatientInfoService.getPatientInfo(id, null);

		verify(loadPatientById).load(id);
		verify(loadPatientByDocument, never()).load(anyString());
	}

	@Test
	void should_get_patient_info_by_document() {
		var document = "12345678909";
		var patient = new Patient("Jhon Doe", document, LocalDate.parse("2000-10-10"), "Lorem Ipsum", "11912341234",
				null, "test@email.com");
		patient.setId(1L);
		doReturn(Optional.of(patient)).when(loadPatientByDocument).load(patient.getDocument());

		getPatientInfoService.getPatientInfo(null, patient.getDocument());

		verify(loadPatientById, never()).load(anyLong());
		verify(loadPatientByDocument).load(document);
	}

}