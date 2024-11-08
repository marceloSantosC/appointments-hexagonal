package com.example.appointments.adapter.in;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointments.application.domain.model.Doctor;
import com.example.appointments.application.port.in.RegisterDoctorUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {

	private final RegisterDoctorUseCase registerDoctorUseCase;

	@PostMapping
	public void registerDoctor(@RequestBody Doctor doctor) {
		var id = registerDoctorUseCase.register(doctor);
	}

}
