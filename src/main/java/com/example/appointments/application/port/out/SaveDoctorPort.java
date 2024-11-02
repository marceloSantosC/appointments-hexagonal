package com.example.appointments.application.port.out;

import com.example.appointments.application.domain.model.Doctor;

public interface SaveDoctorPort {

	Long save(Doctor doctor);

}
