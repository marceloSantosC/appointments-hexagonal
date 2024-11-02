package com.example.appointments.application.port.out;

import java.util.Optional;

import com.example.appointments.application.domain.model.Doctor;

public interface LoadDoctorByDocumentPort {

	Optional<Doctor> load(String document);

}
