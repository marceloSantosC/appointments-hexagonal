package com.example.appointments.application.domain.service;

import com.example.appointments.application.domain.exception.DoctorExistsException;
import com.example.appointments.application.domain.model.Doctor;
import com.example.appointments.application.port.in.RegisterDoctorUseCase;
import com.example.appointments.application.port.out.DoctorPersistencePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterDoctorService implements RegisterDoctorUseCase {

	private final DoctorPersistencePort doctorPersistence;

	@Override
	public Long register(Doctor doctor) {
		if (doctorPersistence.loadByDocument(doctor.getDocument()).isPresent()) {
			throw new DoctorExistsException("doctor already exists");
		}

		return doctorPersistence.save(doctor);
	}
}
