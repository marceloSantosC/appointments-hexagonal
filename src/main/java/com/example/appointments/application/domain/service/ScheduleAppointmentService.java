package com.example.appointments.application.domain.service;

import java.time.LocalDateTime;

import com.example.appointments.application.domain.exception.DoctorDoesNotExistsException;
import com.example.appointments.application.domain.exception.DoctorScheduleNotFreeException;
import com.example.appointments.application.domain.exception.PatientDoesNotExistsException;
import com.example.appointments.application.domain.model.Appointment;
import com.example.appointments.application.port.in.ScheduleAppointmentUseCase;
import com.example.appointments.application.port.out.AppointmentPersistencePort;
import com.example.appointments.application.port.out.DoctorPersistencePort;
import com.example.appointments.application.port.out.PatientPersistencePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScheduleAppointmentService implements ScheduleAppointmentUseCase {

	private final AppointmentPersistencePort appointmentPersistence;

	private final DoctorPersistencePort doctorPersistence;

	private final PatientPersistencePort patientPersistence;

	@Override
	public Long schedule(Appointment appointment) {
		if (!patientPersistence.patientExistsById(appointment.getPatientId())) {
			throw new PatientDoesNotExistsException("Patient does not exists.");
		}

		if (!doctorPersistence.doctorExistsById(appointment.getDoctorId())) {
			throw new DoctorDoesNotExistsException("Doctor does not exists.");
		}

		LocalDateTime appointmentEnd = appointment.getDate().plus(appointment.getEstimatedDuration());
		var isScheduleFree = appointmentPersistence
				.isDoctorScheduleFree(appointment.getDoctorId(), appointment.getDate(), appointmentEnd)
				.orElseThrow(() -> new DoctorDoesNotExistsException("Doctor does not exists."));

		if (!isScheduleFree) {
			throw new DoctorScheduleNotFreeException("Cannot schedule appointment. Doctor schedule not free");
		}

		return appointmentPersistence.save(appointment);
	}
}
