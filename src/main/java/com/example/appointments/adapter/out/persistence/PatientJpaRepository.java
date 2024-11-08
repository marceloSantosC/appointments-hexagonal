package com.example.appointments.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.appointments.adapter.out.persistence.model.PatientEntity;
import com.example.appointments.application.domain.model.Patient;
import com.example.appointments.application.port.out.PatientPersistencePort;

@Repository
public interface PatientJpaRepository extends JpaRepository<PatientEntity, Long>, PatientPersistencePort {

	@Override
	@Query("""
			SELECT new com.example.appointments.application.domain.model.Patient(p.id, p.name, p.document, p.birthDate,
			        p.history, p.phone, p.secondaryPhone, p.email)
			   FROM PatientEntity p WHERE  p.id = :id
			""")
	Optional<Patient> loadById(Long id);

	@Override
	@Query("""
			SELECT new com.example.appointments.application.domain.model.Patient(p.id, p.name, p.document, p.birthDate,
			        p.history, p.phone, p.secondaryPhone, p.email)
			   FROM PatientEntity p WHERE  p.document = :document
			""")
	Optional<Patient> loadByDocument(String document);

	@Override
	default Long save(Patient patient) {
		var patientEntity = PatientEntity.valueOf(patient);
		this.save(patientEntity);
		patient.setId(patientEntity.getId());
		return patientEntity.getId();
	}

	@Override
	default void update(Patient patient) {
		PatientEntity patientEntity = PatientEntity.valueOf(patient);
		patientEntity.setId(patient.getId());
		save(patientEntity);
	}
}
