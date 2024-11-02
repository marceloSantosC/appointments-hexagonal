package com.example.appointments.application.port.in;

import com.example.appointments.application.domain.model.Doctor;

public interface GetDoctorInfoUseCase {

	Doctor getInfo(Long id, String document);

}
