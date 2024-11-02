package com.example.appointments.application.domain.exception;

public class DoctorExistsException extends DoctorException {
	public DoctorExistsException(String message) {
		super(message);
	}
}
