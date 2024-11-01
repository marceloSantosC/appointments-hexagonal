package com.example.appointments.application.domain.exception;

public class PatientExistsException extends PatientException {

	public PatientExistsException(String message) {
		super(message);
	}
}
