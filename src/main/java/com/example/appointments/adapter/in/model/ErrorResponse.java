package com.example.appointments.adapter.in.model;

import java.util.List;

public record ErrorResponse(int status, String message, List<ErrorDetail> errors) {

	public record ErrorDetail(String message, String field) {
	}

}
