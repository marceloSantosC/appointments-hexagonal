package com.example.appointments.application.port.in.model;

import java.time.LocalTime;

public record UpdateDoctorModel(String name, String phone, String secondaryPhone, String email,
		LocalTime workingHourStart, LocalTime workingHourEnd) {
}
