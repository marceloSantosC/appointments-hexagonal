package com.example.appointments.application.domain.service;

import com.example.appointments.application.domain.exception.PatientDoesNotExistsException;
import com.example.appointments.application.domain.model.Patient;
import com.example.appointments.application.port.in.GetPatientInfoUseCase;
import com.example.appointments.application.port.out.LoadPatientByDocumentPort;
import com.example.appointments.application.port.out.LoadPatientByIdPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetPatientInfoService implements GetPatientInfoUseCase {

	private final LoadPatientByIdPort loadPatientByIdPort;

	private final LoadPatientByDocumentPort loadPatientByDocumentPort;

	@Override
	public Patient getPatientInfo(Long id, String document) {
		if (document != null) {
			return loadPatientByDocumentPort.load(document)
					.orElseThrow(() -> new PatientDoesNotExistsException("patient not found."));
		}
		return loadPatientByIdPort.load(id).orElseThrow(() -> new PatientDoesNotExistsException("patient not found."));
	}
}
