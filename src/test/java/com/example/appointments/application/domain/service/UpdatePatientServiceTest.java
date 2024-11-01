package com.example.appointments.application.domain.service;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.appointments.application.domain.model.Patient;
import com.example.appointments.application.port.in.model.UpdatePatientModel;
import com.example.appointments.application.port.out.LoadPatientByIdPort;
import com.example.appointments.application.port.out.UpdatePatientPort;

@ExtendWith(MockitoExtension.class)
class UpdatePatientServiceTest {

	@InjectMocks
	private UpdatePatientService service;

	@Mock
	private UpdatePatientPort updatePatient;

	@Mock
	private LoadPatientByIdPort loadPatientById;

	@Test
	void should_update_patient_info() {

		Long id = 1L;
		var model = new UpdatePatientModel("Jhon Doe", LocalDate.parse("2000-10-10"), "Lorem Ipsum", "11912341234",
				null, "test@email.com");

		var patient = new Patient("Jhon Doe", "12345678909", LocalDate.parse("2000-10-10"), "Lorem Ipsum",
				"11912341234", null, "test@email.com");
		patient.setId(id);
		doReturn(Optional.of(patient)).when(loadPatientById).load(id);

		service.update(id, model);

		verify(loadPatientById).load(id);
		verify(updatePatient).update(patient);
	}

}