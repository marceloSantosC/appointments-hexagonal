package com.example.appointments.adapter.in.model;

import java.time.LocalDate;

import com.example.appointments.application.port.in.model.UpdatePatientModel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdatePatientRequest(@Size(min = 8, message = "name is invalid") String name,
		@Past(message = "invalid birth date") LocalDate birthDate,
		@Size(min = 1, message = "history is invalid") String history,
		@Pattern(regexp = "\\d{11}", message = "phone is invalid") String phone,
		@Pattern(regexp = "\\d{11}", message = "secondaryPhone is invalid") String secondaryPhone,
		@Email(message = "email is invalid") String email) {

	public UpdatePatientModel toDomainEntity() {
		return new UpdatePatientModel(name, birthDate, history, phone, secondaryPhone, email);
	}
}
