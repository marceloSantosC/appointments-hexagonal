package com.example.appointments.application.port.in;

import com.example.appointments.application.domain.model.Patient;

public interface GetPatientInfoUseCase {

	Patient getPatientInfo(Long id, String document);

}
