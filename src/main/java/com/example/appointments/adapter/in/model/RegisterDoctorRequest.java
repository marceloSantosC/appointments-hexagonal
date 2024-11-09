package com.example.appointments.adapter.in.model;

import java.time.LocalTime;

import org.hibernate.validator.constraints.br.CPF;

import com.example.appointments.application.domain.model.Doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterDoctorRequest(@NotBlank(message = "name is required.") String name,
		@NotBlank(message = "speciality is required.") String speciality,
		@NotBlank(message = "phone is required.") @Pattern(regexp = "\\d{11}", message = "phone is invalid") String phone,
		@NotBlank(message = "secondaryPhone is required.") @Pattern(regexp = "\\d{11}", message = "secondaryPhone is invalid") String secondaryPhone,
		@NotBlank(message = "email is required.") @Email(message = "email is invalid") String email,
		@NotBlank(message = "crm is required.") String crm,
		@NotBlank(message = "document is required.") @CPF(message = "document is invalid") String document,
		@NotNull(message = "workingHourStart is invalid") LocalTime workingHourStart,
		@NotNull(message = "workingHourEnd is invalid") LocalTime workingHourEnd) {

	public Doctor toDomainEntity() {
		return Doctor.builder().name(name).speciality(speciality).phone(phone).secondaryPhone(secondaryPhone)
				.email(email).crm(crm).document(document).workingHourStart(workingHourStart)
				.workingHourEnd(workingHourEnd).build();
	}
}
