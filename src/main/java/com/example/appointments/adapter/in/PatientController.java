package com.example.appointments.adapter.in;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.appointments.adapter.in.model.RegisterPatientRequest;
import com.example.appointments.adapter.in.model.UpdatePatientRequest;
import com.example.appointments.application.domain.model.Patient;
import com.example.appointments.application.port.in.GetPatientInfoUseCase;
import com.example.appointments.application.port.in.RegisterPatientUseCase;
import com.example.appointments.application.port.in.UpdatePatientUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

	private final RegisterPatientUseCase registerPatientUseCase;

	private final GetPatientInfoUseCase getPatientInfoUseCase;

	private final UpdatePatientUseCase updatePatientUseCase;

	@PostMapping
	public ResponseEntity<Void> registerPatient(@RequestBody @Valid RegisterPatientRequest patientRequest) {
		var id = registerPatientUseCase.register(patientRequest.toDomainEntity());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().queryParam("id", id).build().toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public Patient getPatientInfo(@RequestParam(required = false) Long id,
			@RequestParam(required = false) String document) {
		return getPatientInfoUseCase.getPatientInfo(id, document);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePatientInfo(@PathVariable Long id, @RequestBody @Valid UpdatePatientRequest request) {
		updatePatientUseCase.update(id, request.toDomainEntity());
	}

}
