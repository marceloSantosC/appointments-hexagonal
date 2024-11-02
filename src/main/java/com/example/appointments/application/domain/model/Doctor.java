package com.example.appointments.application.domain.model;

import java.util.Objects;

import lombok.Getter;

@Getter
public class Doctor {

	private final Long id;

	private String name;

	private final String speciality;

	private String phone;

	private String secondaryPhone;

	private String email;

	private final String crm;

	private final String document;

	public Doctor(Long id, String name, String speciality, String phone, String secondaryPhone, String email,
			String crm, String document) {
		this.id = id;
		this.name = Objects.requireNonNull(name);
		this.speciality = Objects.requireNonNull(speciality);
		this.phone = Objects.requireNonNull(phone);
		this.secondaryPhone = secondaryPhone;
		this.email = Objects.requireNonNull(email);
		this.crm = Objects.requireNonNull(crm);
		this.document = Objects.requireNonNull(document);
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
