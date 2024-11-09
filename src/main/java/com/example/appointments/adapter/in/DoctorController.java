package com.example.appointments.adapter.in;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.appointments.adapter.in.model.RegisterDoctorRequest;
import com.example.appointments.application.port.in.RegisterDoctorUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {

	private final RegisterDoctorUseCase registerDoctorUseCase;

	@PostMapping
	public ResponseEntity<Void> registerDoctor(@RequestBody @Valid RegisterDoctorRequest doctorRequest) {
		var id = registerDoctorUseCase.register(doctorRequest.toDomainEntity());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(id.toString()).build().toUri();
		return ResponseEntity.created(uri).build();
	}

}
