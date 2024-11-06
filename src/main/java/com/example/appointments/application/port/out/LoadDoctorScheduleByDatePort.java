package com.example.appointments.application.port.out;

import java.time.LocalDateTime;
import java.util.List;

import com.example.appointments.application.domain.model.DoctorSchedule;

public interface LoadDoctorScheduleByDatePort {

	List<DoctorSchedule> load(Long doctorId, LocalDateTime start, LocalDateTime end);

}
