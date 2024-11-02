package com.example.appointments.application.port.in;

import com.example.appointments.application.domain.model.Doctor;

public interface RegisterDoctorUseCase {

	Long register(Doctor doctor);

}
