package com.example.appointments.application.domain.exception;

public class DoctorDoesNotExistsException extends DoctorException {
	public DoctorDoesNotExistsException(String message) {
		super(message);
	}
}
