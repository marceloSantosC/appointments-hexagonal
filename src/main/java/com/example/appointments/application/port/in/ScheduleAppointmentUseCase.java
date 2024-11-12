package com.example.appointments.application.port.in;

import com.example.appointments.application.domain.model.Appointment;

public interface ScheduleAppointmentUseCase {

	Long schedule(Appointment appointment);

}
