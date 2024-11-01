package com.example.appointments.application.domain.service;

import com.example.appointments.application.domain.exception.PatientDoesNotExistsException;
import com.example.appointments.application.port.in.UpdatePatientUseCase;
import com.example.appointments.application.port.in.model.UpdatePatientModel;
import com.example.appointments.application.port.out.LoadPatientByIdPort;
import com.example.appointments.application.port.out.UpdatePatientPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdatePatientService implements UpdatePatientUseCase {

	private final UpdatePatientPort updatePatientPort;

	private final LoadPatientByIdPort loadPatientByIdPort;

	@Override
	public void update(Long id, UpdatePatientModel model) {
		var patient = loadPatientByIdPort.load(id)
				.orElseThrow(() -> new PatientDoesNotExistsException("patient does not exists"));
		patient.setName(model.name());
		patient.setHistory(model.history());
		patient.setBirthDate(model.birthDate());
		patient.setPhone(model.phone());
		patient.setSecondaryPhone(model.secondaryPhone());
		patient.setEmail(model.email());
		updatePatientPort.update(patient);
	}
}
