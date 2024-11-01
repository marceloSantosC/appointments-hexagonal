package com.example.appointments.application.port.in;

import com.example.appointments.application.port.in.model.UpdatePatientModel;

public interface UpdatePatientUseCase {

	void update(Long patientId, UpdatePatientModel model);

}
