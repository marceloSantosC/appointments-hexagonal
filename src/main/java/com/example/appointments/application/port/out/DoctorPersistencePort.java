package com.example.appointments.application.port.out;

import java.util.Optional;

import com.example.appointments.application.domain.model.Doctor;

public interface DoctorPersistencePort {

	Optional<Doctor> loadByDocument(String document);

	Optional<Doctor> loadById(Long id);

	Long save(Doctor doctor);

	void update(Doctor doctor);

	boolean doctorExistsById(Long id);

}
