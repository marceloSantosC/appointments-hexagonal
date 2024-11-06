package com.example.appointments.application.domain.service;

import com.example.appointments.application.domain.exception.DoctorDoesNotExistsException;
import com.example.appointments.application.domain.model.Doctor;
import com.example.appointments.application.port.in.GetDoctorInfoUseCase;
import com.example.appointments.application.port.out.DoctorPersistencePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetDoctorInfoService implements GetDoctorInfoUseCase {

	private final DoctorPersistencePort doctorPersistence;

	@Override
	public Doctor getInfo(Long id, String document) {

		if (document != null) {
			return doctorPersistence.loadByDocument(document)
					.orElseThrow(() -> new DoctorDoesNotExistsException("doctor does not exists"));
		}

		return doctorPersistence.loadById(id)
				.orElseThrow(() -> new DoctorDoesNotExistsException("doctor does not exists"));
	}
}
