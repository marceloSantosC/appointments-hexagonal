package com.example.appointments.application.domain.model;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;

import com.example.appointments.application.domain.exception.DoctorException;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Doctor {

	private final Long id;

	private String name;

	private final String speciality;

	private String phone;

	private String secondaryPhone;

	private String email;

	private final String crm;

	private final String document;

	private LocalTime workingHourStart;

	private LocalTime workingHourEnd;

	public Doctor(Long id, String name, String speciality, String phone, String secondaryPhone, String email,
			String crm, String document, LocalTime workingHourStart, LocalTime workingHourEnd) {
		this.id = id;
		this.name = Objects.requireNonNull(name);
		this.speciality = Objects.requireNonNull(speciality);
		this.phone = Objects.requireNonNull(phone);
		this.secondaryPhone = secondaryPhone;
		this.email = Objects.requireNonNull(email);
		this.crm = Objects.requireNonNull(crm);
		this.document = Objects.requireNonNull(document);
		this.workingHourStart = Objects.requireNonNull(workingHourStart);
		this.workingHourEnd = Objects.requireNonNull(workingHourEnd);
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

	public void updateWorkingHours(LocalTime start, LocalTime end) {
		if (start == null && end == null) {
			return;
		}

		start = Optional.ofNullable(start).orElse(workingHourStart);
		end = Optional.ofNullable(end).orElse(workingHourEnd);

		if (start.isAfter(end)) {
			throw new DoctorException("invalid working hours: end should be after the start.");
		}

		this.workingHourStart = start;
		this.workingHourEnd = end;
	}
}
