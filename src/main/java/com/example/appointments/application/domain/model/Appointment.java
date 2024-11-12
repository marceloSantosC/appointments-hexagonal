package com.example.appointments.application.domain.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Appointment {

	@Setter
	private Long id;

	private Long doctorId;

	private Long patientId;

	private Duration estimatedDuration;

	private LocalDateTime date;

	private String observations;

	public Appointment(Long doctorId, Long patientId, Duration estimatedDuration, LocalDateTime date,
			String observations) {
		this.doctorId = Objects.requireNonNull(doctorId);
		this.patientId = Objects.requireNonNull(patientId);
		this.estimatedDuration = Objects.requireNonNull(estimatedDuration);
		this.date = Objects.requireNonNull(date);
		this.observations = Objects.requireNonNull(observations);
	}
}
