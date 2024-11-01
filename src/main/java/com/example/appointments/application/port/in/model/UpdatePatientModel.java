package com.example.appointments.application.port.in.model;

import java.time.LocalDate;

public record UpdatePatientModel(String name, LocalDate birthDate, String history, String phone, String secondaryPhone,
		String email) {
}
