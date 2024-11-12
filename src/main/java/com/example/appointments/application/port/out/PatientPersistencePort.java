package com.example.appointments.application.port.out;

import java.util.Optional;

import com.example.appointments.application.domain.model.Patient;

public interface PatientPersistencePort {

	Optional<Patient> loadById(Long id);

	Optional<Patient> loadByDocument(String document);

	Long save(Patient patient);

	void update(Patient patient);

	boolean patientExistsById(Long id);

}
