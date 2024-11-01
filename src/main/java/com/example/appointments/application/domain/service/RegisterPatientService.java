package com.example.appointments.application.domain.service;

import com.example.appointments.application.domain.exception.PatientExistsException;
import com.example.appointments.application.domain.model.Patient;
import com.example.appointments.application.port.in.RegisterPatientUseCase;
import com.example.appointments.application.port.out.LoadPatientByDocumentPort;
import com.example.appointments.application.port.out.SavePatientPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterPatientService implements RegisterPatientUseCase {

	private final SavePatientPort savePatientPort;

	private final LoadPatientByDocumentPort loadPatientByDocumentPort;

	@Override
	public Long register(Patient patient) {
		var existingPatient = loadPatientByDocumentPort.load(patient.document());

		if (existingPatient.isPresent()) {
			throw new PatientExistsException("patient already exists");
		}

		return savePatientPort.save(patient);
	}
}
