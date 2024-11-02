package com.example.appointments.application.domain.service;

import com.example.appointments.application.domain.exception.DoctorDoesNotExistsException;
import com.example.appointments.application.port.in.UpdateDoctorUseCase;
import com.example.appointments.application.port.in.model.UpdateDoctorModel;
import com.example.appointments.application.port.out.LoadDoctorByIdPort;
import com.example.appointments.application.port.out.UpdateDoctorPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateDoctorService implements UpdateDoctorUseCase {

	private final LoadDoctorByIdPort loadDoctorById;

	private final UpdateDoctorPort updateDoctor;

	@Override
	public void update(Long id, UpdateDoctorModel model) {
		var doctor = loadDoctorById.load(id).orElseThrow(() -> new DoctorDoesNotExistsException("doctor not found"));
		doctor.setName(model.name());
		doctor.setEmail(model.email());
		doctor.setSecondaryPhone(model.secondaryPhone());
		doctor.setPhone(model.phone());
		updateDoctor.update(doctor);
	}
}
