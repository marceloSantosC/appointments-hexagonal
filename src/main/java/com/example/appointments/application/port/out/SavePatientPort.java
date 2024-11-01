package com.example.appointments.application.port.out;

import com.example.appointments.application.domain.model.Patient;

public interface SavePatientPort {

	Long save(Patient patient);

}
