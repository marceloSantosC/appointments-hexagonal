package com.example.appointments.application.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.appointments.application.domain.exception.PatientExistsException;
import com.example.appointments.application.domain.model.Patient;
import com.example.appointments.application.port.out.PatientPersistencePort;

@ExtendWith(MockitoExtension.class)
class RegisterPatientServiceTest {

	@InjectMocks
	private RegisterPatientService service;

	@Mock
	private PatientPersistencePort patientPersistencePort;

	@Test
	void should_register_patient() {

		var patient = new Patient("Jhon Doe", "12345678909", LocalDate.parse("2000-10-10"), "Lorem Ipsum",
				"11912341234", null, "test@email.com");

		doReturn(Optional.empty()).when(patientPersistencePort).loadByDocument(patient.getDocument());
		doReturn(1L).when(patientPersistencePort).save(patient);

		service.register(patient);

		verify(patientPersistencePort).loadByDocument(patient.getDocument());
		verify(patientPersistencePort).save(patient);

	}

	@Test
	void should_not_register_existing_patient() {

		var patient = new Patient("Jhon Doe", "12345678909", LocalDate.parse("2000-10-10"), "Lorem Ipsum",
				"11912341234", null, "test@email.com");

		doReturn(Optional.of(patient)).when(patientPersistencePort).loadByDocument(patient.getDocument());

		assertThrows(PatientExistsException.class, () -> service.register(patient));

		verify(patientPersistencePort).loadByDocument(patient.getDocument());
		verify(patientPersistencePort, never()).save(patient);
	}

}