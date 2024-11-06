package com.example.appointments.application.port.in;

import java.util.List;

import com.example.appointments.application.domain.model.DoctorSchedule;
import com.example.appointments.application.port.in.model.DoctorScheduleFilterModel;

public interface GetDoctorScheduleUseCase {

	List<DoctorSchedule> get(DoctorScheduleFilterModel model);

}
