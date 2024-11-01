package com.example.appointments.application.port.in;

import com.example.appointments.application.domain.model.Patient;

public interface RegisterPatientUseCase {

	Long register(Patient patient);

}
