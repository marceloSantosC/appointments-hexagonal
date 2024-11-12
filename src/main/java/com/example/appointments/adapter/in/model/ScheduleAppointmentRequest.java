package com.example.appointments.adapter.in.model;

import java.time.Duration;
import java.time.LocalDateTime;

import com.example.appointments.application.domain.model.Appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ScheduleAppointmentRequest(@NotNull(message = "doctorId is required.") Long doctorId,
		@NotNull(message = "patientId is required.") Long patientId,
		@NotNull(message = "estimatedDuration is required") Duration estimatedDuration,
		@NotNull(message = "start is required") @Future(message = "start should be a future date") LocalDateTime start,
		@NotBlank(message = "observations is required") String observations) {

	public Appointment toDomainEntity() {
		return new Appointment(doctorId, patientId, estimatedDuration, start, observations);
	}

}
