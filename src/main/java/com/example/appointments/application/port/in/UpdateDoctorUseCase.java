package com.example.appointments.application.port.in;

import com.example.appointments.application.port.in.model.UpdateDoctorModel;

public interface UpdateDoctorUseCase {

	void update(Long id, UpdateDoctorModel model);

}
