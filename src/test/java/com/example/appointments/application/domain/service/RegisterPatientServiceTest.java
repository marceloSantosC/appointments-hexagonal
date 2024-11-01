package com.example.appointments.application.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.appointments.application.domain.exception.PatientExistsException;
import com.example.appointments.application.domain.model.Contact;
import com.example.appointments.application.domain.model.Patient;
import com.example.appointments.application.port.out.LoadPatientByDocumentPort;
import com.example.appointments.application.port.out.SavePatientPort;

@ExtendWith(MockitoExtension.class)
class RegisterPatientServiceTest {

	@InjectMocks
	private RegisterPatientService service;

	@Mock
	private SavePatientPort savePatient;

	@Mock
	private LoadPatientByDocumentPort loadPatientByDocument;

	@Test
	void should_register_patient() {

		var contacts = List.of(new Contact(true, "11912341234", Contact.ContactType.PHONE));
		var patient = new Patient(null, "Jhon Doe", "12345678909", LocalDate.parse("2000-10-10"), "Lorem Ipsum",
				contacts);

		doReturn(Optional.empty()).when(loadPatientByDocument).load(patient.document());
		doReturn(1L).when(savePatient).save(patient);

		service.register(patient);

		verify(loadPatientByDocument).load(patient.document());
		verify(savePatient).save(patient);

	}

	@Test
	void should_not_register_existing_patient() {

		var contacts = List.of(new Contact(true, "11912341234", Contact.ContactType.PHONE));
		var patient = new Patient(null, "Jhon Doe", "12345678909", LocalDate.parse("2000-10-10"), "Lorem Ipsum",
				contacts);

		doReturn(Optional.of(patient)).when(loadPatientByDocument).load(patient.document());

		assertThrows(PatientExistsException.class, () -> service.register(patient));

		verify(loadPatientByDocument).load(patient.document());
		verify(savePatient, never()).save(patient);
	}

}