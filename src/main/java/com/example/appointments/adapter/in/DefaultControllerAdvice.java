package com.example.appointments.adapter.in;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.appointments.adapter.in.model.ErrorResponse;
import com.example.appointments.application.domain.exception.DoctorException;
import com.example.appointments.application.domain.exception.PatientException;

@ControllerAdvice
public class DefaultControllerAdvice extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<ErrorResponse.ErrorDetail> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(f -> new ErrorResponse.ErrorDetail(f.getDefaultMessage(), f.getField())).toList();
		status = HttpStatus.BAD_REQUEST;
		var response = new ErrorResponse(status.value(), ((HttpStatus) status).getReasonPhrase(), errors);
		return ResponseEntity.status(status).body(response);
	}

	@ExceptionHandler(DoctorException.class)
	public ResponseEntity<ErrorResponse> handleDoctorException(DoctorException e) {
		var status = HttpStatus.UNPROCESSABLE_ENTITY;
		var response = new ErrorResponse(status.value(), e.getMessage(), Collections.emptyList());
		return ResponseEntity.status(status).body(response);
	}

	@ExceptionHandler(PatientException.class)
	public ResponseEntity<ErrorResponse> handleDoctorException(PatientException e) {
		var status = HttpStatus.UNPROCESSABLE_ENTITY;
		var response = new ErrorResponse(status.value(), e.getMessage(), Collections.emptyList());
		return ResponseEntity.status(status).body(response);
	}
}
