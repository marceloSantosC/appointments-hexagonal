package com.example.appointments.application.port.out;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.appointments.application.domain.model.Appointment;

public interface AppointmentPersistencePort {

	Optional<Boolean> isDoctorScheduleFree(Long doctorId, LocalDateTime start, LocalDateTime end);

	Long save(Appointment appointment);

}
