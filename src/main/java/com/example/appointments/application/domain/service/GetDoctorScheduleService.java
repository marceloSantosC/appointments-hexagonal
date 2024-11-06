package com.example.appointments.application.domain.service;

import java.util.List;

import com.example.appointments.application.domain.model.DoctorSchedule;
import com.example.appointments.application.port.in.GetDoctorScheduleUseCase;
import com.example.appointments.application.port.in.model.DoctorScheduleFilterModel;
import com.example.appointments.application.port.out.LoadDoctorScheduleByDatePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetDoctorScheduleService implements GetDoctorScheduleUseCase {

	private final LoadDoctorScheduleByDatePort loadDoctorScheduleByDatePort;

	@Override
	public List<DoctorSchedule> get(DoctorScheduleFilterModel model) {
		return loadDoctorScheduleByDatePort.load(model.doctorId(), model.start(), model.end());
	}
}
