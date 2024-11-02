package com.example.appointments.application.domain.service;

import com.example.appointments.application.domain.exception.DoctorExistsException;
import com.example.appointments.application.domain.model.Doctor;
import com.example.appointments.application.port.in.RegisterDoctorUseCase;
import com.example.appointments.application.port.out.LoadDoctorByDocumentPort;
import com.example.appointments.application.port.out.SaveDoctorPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterDoctorService implements RegisterDoctorUseCase {

	private final SaveDoctorPort saveDoctor;

	private final LoadDoctorByDocumentPort loadDoctorByDocument;

	@Override
	public Long register(Doctor doctor) {
		if (loadDoctorByDocument.load(doctor.getDocument()).isPresent()) {
			throw new DoctorExistsException("doctor already exists");
		}

		return saveDoctor.save(doctor);
	}
}
