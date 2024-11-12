package com.example.appointments.adapter.in;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.appointments.adapter.in.model.ScheduleAppointmentRequest;
import com.example.appointments.application.port.in.ScheduleAppointmentUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {

	private final ScheduleAppointmentUseCase scheduleAppointmentUseCase;

	@PostMapping
	public ResponseEntity<Void> scheduleAppointment(@RequestBody @Valid ScheduleAppointmentRequest appointmentRequest) {
		var id = scheduleAppointmentUseCase.schedule(appointmentRequest.toDomainEntity());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().queryParam("id", id).build().toUri();
		return ResponseEntity.created(uri).build();
	}

}
