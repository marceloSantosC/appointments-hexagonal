package com.example.appointments.application.domain.service;

import com.example.appointments.application.domain.exception.DoctorDoesNotExistsException;
import com.example.appointments.application.domain.model.Doctor;
import com.example.appointments.application.port.in.GetDoctorInfoUseCase;
import com.example.appointments.application.port.out.LoadDoctorByDocumentPort;
import com.example.appointments.application.port.out.LoadDoctorByIdPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetDoctorInfoService implements GetDoctorInfoUseCase {

	private final LoadDoctorByDocumentPort loadDoctorByDocument;

	private final LoadDoctorByIdPort loadDoctorByIdPort;

	@Override
	public Doctor getInfo(Long id, String document) {

		if (document != null) {
			return loadDoctorByDocument.load(document)
					.orElseThrow(() -> new DoctorDoesNotExistsException("doctor does not exists"));
		}

		return loadDoctorByIdPort.load(id)
				.orElseThrow(() -> new DoctorDoesNotExistsException("doctor does not exists"));
	}
}
