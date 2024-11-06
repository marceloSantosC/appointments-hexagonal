package com.example.appointments.application.domain.service;

import com.example.appointments.application.domain.exception.PatientDoesNotExistsException;
import com.example.appointments.application.domain.model.Patient;
import com.example.appointments.application.port.in.GetPatientInfoUseCase;
import com.example.appointments.application.port.out.PatientPersistencePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetPatientInfoService implements GetPatientInfoUseCase {

	private final PatientPersistencePort patientPersistencePort;

	@Override
	public Patient getPatientInfo(Long id, String document) {
		if (document != null) {
			return patientPersistencePort.loadByDocument(document)
					.orElseThrow(() -> new PatientDoesNotExistsException("patient not found."));
		}
		return patientPersistencePort.loadById(id)
				.orElseThrow(() -> new PatientDoesNotExistsException("patient not found."));
	}
}
