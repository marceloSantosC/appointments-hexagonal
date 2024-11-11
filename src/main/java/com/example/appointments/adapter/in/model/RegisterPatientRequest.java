package com.example.appointments.adapter.in.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.example.appointments.application.domain.model.Patient;

import jakarta.validation.constraints.*;

public record RegisterPatientRequest(@NotBlank(message = "name is required") String name,
		@NotBlank(message = "document is required.") @CPF(message = "document is invalid") String document,
		@Past(message = "invalid birth date") @NotNull(message = "birthDate is required") LocalDate birthDate,
		@NotBlank(message = "history is required") String history,
		@Pattern(regexp = "\\d{11}", message = "phone is invalid") String phone,
		@Pattern(regexp = "\\d{11}", message = "secondaryPhone is invalid") String secondaryPhone,
		@Email(message = "email is invalid") String email) {

	public Patient toDomainEntity() {
		return new Patient(name, document, birthDate, history, phone, secondaryPhone, email);
	}

}
