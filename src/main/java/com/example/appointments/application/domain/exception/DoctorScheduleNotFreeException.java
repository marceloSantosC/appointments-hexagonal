package com.example.appointments.application.domain.exception;

public class DoctorScheduleNotFreeException extends DoctorException {
	public DoctorScheduleNotFreeException(String message) {
		super(message);
	}
}
