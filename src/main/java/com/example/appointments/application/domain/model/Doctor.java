package com.example.appointments.application.domain.model;

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
		this.name = name;
		this.speciality = speciality;
		this.phone = phone;
		this.secondaryPhone = secondaryPhone;
		this.email = email;
		this.crm = crm;
		this.document = document;
	}

}
