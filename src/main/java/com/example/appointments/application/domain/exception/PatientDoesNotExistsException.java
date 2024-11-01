package com.example.appointments.application.domain.exception;

public class PatientDoesNotExistsException extends PatientException {
	public PatientDoesNotExistsException(String message) {
		super(message);
	}
}
