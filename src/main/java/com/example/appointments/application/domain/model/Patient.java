package com.example.appointments.application.domain.model;

import java.time.LocalDate;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Patient {

	@Setter
	private Long id;

	private String name;

	private final String document;

	private LocalDate birthDate;

	private String history;

	private String phone;

	private String secondaryPhone;

	private String email;

	public Patient(String name, String document, LocalDate birthDate, String history, String phone, String altPhone,
			String email) {
		this.id = null;
		this.name = Objects.requireNonNull(name);
		this.document = Objects.requireNonNull(document);
		this.birthDate = Objects.requireNonNull(birthDate);
		this.history = Objects.requireNonNull(history);
		this.phone = Objects.requireNonNull(phone);
		this.secondaryPhone = altPhone;
		this.email = Objects.requireNonNull(email);
	}

	public void setHistory(String history) {
		if (history != null)
			this.history = history;
	}

	public void setBirthDate(LocalDate birthDate) {
		if (birthDate != null)
			this.birthDate = birthDate;
	}

	public void setName(String name) {
		if (name != null)
			this.name = name;

	}

	public void setPhone(String phone) {
		if (phone != null)
			this.phone = phone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		if (secondaryPhone != null)
			this.secondaryPhone = secondaryPhone;
	}

	public void setEmail(String email) {
		if (email != null)
			this.email = email;
	}

}
