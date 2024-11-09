package com.example.appointments.adapter.in.model;

import java.time.LocalTime;

import com.example.appointments.application.port.in.model.UpdateDoctorModel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateDoctorRequest(@Size(min = 1, message = "name is invalid") String name,
		@Pattern(regexp = "\\d{11}", message = "phone is invalid") String phone,
		@Pattern(regexp = "\\d{11}", message = "phone is invalid") String secondaryPhone,
		@Email(message = "email is invalid") String email, LocalTime workingHourStart, LocalTime workingHourEnd) {

	public UpdateDoctorModel toUpdateDoctorModel() {
		return new UpdateDoctorModel(name, phone, secondaryPhone, email, workingHourStart, workingHourEnd);
	}
}
