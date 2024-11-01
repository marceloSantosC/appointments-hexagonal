package com.example.appointments.application.domain.model;

import java.time.LocalDate;
import java.util.List;

public record Patient(Long id, String name, String document, LocalDate birthDate, String history,
		List<Contact> contactInfo) {
}
