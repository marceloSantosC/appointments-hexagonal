package com.example.appointments.adapter.in;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.appointments.adapter.in.model.RegisterDoctorRequest;
import com.example.appointments.adapter.in.model.UpdateDoctorRequest;
import com.example.appointments.application.domain.model.Doctor;
import com.example.appointments.application.domain.model.DoctorSchedule;
import com.example.appointments.application.domain.service.GetDoctorScheduleService;
import com.example.appointments.application.port.in.GetDoctorInfoUseCase;
import com.example.appointments.application.port.in.RegisterDoctorUseCase;
import com.example.appointments.application.port.in.UpdateDoctorUseCase;
import com.example.appointments.application.port.in.model.DoctorScheduleFilterModel;
import com.example.appointments.application.port.in.model.UpdateDoctorModel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {

	private final RegisterDoctorUseCase registerDoctorUseCase;

	private final GetDoctorInfoUseCase getDoctorInfoUseCase;

	private final UpdateDoctorUseCase updateDoctorUseCase;

	private final GetDoctorScheduleService doctorScheduleService;

	@PostMapping
	public ResponseEntity<Void> registerDoctor(@RequestBody @Valid RegisterDoctorRequest doctorRequest) {
		var id = registerDoctorUseCase.register(doctorRequest.toDomainEntity());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().queryParam("id", id).build().toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public Doctor getDoctorInfo(@RequestParam(required = false) String document,
			@RequestParam(required = false) Long id) {
		return getDoctorInfoUseCase.getInfo(id, document);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateDoctor(@PathVariable Long id, @RequestBody UpdateDoctorRequest updateDoctorRequest) {
		UpdateDoctorModel model = updateDoctorRequest.toUpdateDoctorModel();
		updateDoctorUseCase.update(id, model);
	}

	@GetMapping("/{id}/schedule")
	public List<DoctorSchedule> getDoctorSchedule(@PathVariable Long id, @RequestParam LocalDateTime start,
			@RequestParam LocalDateTime end) {
		return doctorScheduleService.get(new DoctorScheduleFilterModel(id, start, end));
	}

}
