package com.example.appointments.application.domain.model;

public record Contact(boolean primary, String info, ContactType type) {

	public enum ContactType {
		PHONE, EMAIL
	}

}
