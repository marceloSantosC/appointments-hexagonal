package com.example.appointments.application.domain.service;

import com.example.appointments.application.domain.exception.PatientExistsException;
import com.example.appointments.application.domain.model.Patient;
import com.example.appointments.application.port.in.RegisterPatientUseCase;
import com.example.appointments.application.port.out.PatientPersistencePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterPatientService implements RegisterPatientUseCase {

	private final PatientPersistencePort patientPersistencePort;

	@Override
	public Long register(Patient patient) {
		var existingPatient = patientPersistencePort.loadByDocument(patient.getDocument());

		if (existingPatient.isPresent()) {
			throw new PatientExistsException("patient already exists");
		}

		return patientPersistencePort.save(patient);
	}
}
