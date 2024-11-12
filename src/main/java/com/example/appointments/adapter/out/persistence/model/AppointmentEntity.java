package com.example.appointments.adapter.out.persistence.model;

import java.time.Duration;
import java.time.LocalDateTime;

import com.example.appointments.application.domain.model.Appointment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "AppointmentEntity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Appointment")
public class AppointmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long doctorId;

	private Long patientId;

	private Duration estimatedDuration;

	private LocalDateTime start;

	private LocalDateTime estimatedEnd;

	private String observations;

	public static AppointmentEntity valueOf(Appointment appointment) {
		return AppointmentEntity.builder().doctorId(appointment.getDoctorId()).patientId(appointment.getPatientId())
				.estimatedDuration(appointment.getEstimatedDuration()).start(appointment.getDate())
				.estimatedEnd(appointment.getDate().plus(appointment.getEstimatedDuration()))
				.observations(appointment.getObservations()).build();
	}

}
