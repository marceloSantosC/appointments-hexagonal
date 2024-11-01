package com.example.appointments.application.port.out;

import java.util.Optional;

import com.example.appointments.application.domain.model.Patient;

public interface LoadPatientByDocumentPort {

	Optional<Patient> load(String document);

}
