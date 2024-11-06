package com.example.appointments.application.domain.service;

import com.example.appointments.application.domain.exception.DoctorDoesNotExistsException;
import com.example.appointments.application.port.in.UpdateDoctorUseCase;
import com.example.appointments.application.port.in.model.UpdateDoctorModel;
import com.example.appointments.application.port.out.DoctorPersistencePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateDoctorService implements UpdateDoctorUseCase {

	private final DoctorPersistencePort doctorPersistence;

	@Override
	public void update(Long id, UpdateDoctorModel model) {
		var doctor = doctorPersistence.loadById(id)
				.orElseThrow(() -> new DoctorDoesNotExistsException("doctor not found"));
		doctor.setName(model.name());
		doctor.setEmail(model.email());
		doctor.setSecondaryPhone(model.secondaryPhone());
		doctor.setPhone(model.phone());
		doctorPersistence.update(doctor);
	}
}
