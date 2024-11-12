package com.example.appointments.application.port.out;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.appointments.application.domain.model.Doctor;
import com.example.appointments.application.domain.model.DoctorSchedule;

public interface DoctorPersistencePort {

	Optional<Doctor> loadByDocument(String document);

	Optional<Doctor> loadById(Long id);

	Long save(Doctor doctor);

	void update(Doctor doctor);

	List<DoctorSchedule> loadSchedule(Long doctorId, LocalDateTime start, LocalDateTime end);

	boolean doctorExistsById(Long id);

}
