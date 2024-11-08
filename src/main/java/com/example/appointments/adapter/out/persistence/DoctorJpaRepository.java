package com.example.appointments.adapter.out.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.appointments.adapter.out.persistence.model.DoctorEntity;
import com.example.appointments.application.domain.model.Doctor;
import com.example.appointments.application.domain.model.DoctorSchedule;
import com.example.appointments.application.port.out.DoctorPersistencePort;

@Repository
public interface DoctorJpaRepository extends JpaRepository<DoctorEntity, Long>, DoctorPersistencePort {

	@Query("""
			    SELECT new com.example.appointments.application.domain.model.Doctor(e.id, e.name, e.speciality, e.phone,
			                            e.secondaryPhone, e.email, e.crm, e.document, e.workingHourStart, e.workingHoursEnd)
			    FROM DoctorEntity e
			     WHERE e.id = :id
			""")
	Optional<Doctor> loadById(Long id);

	@Query("""
			    SELECT new com.example.appointments.application.domain.model.Doctor(e.id, e.name, e.speciality, e.phone,
			                            e.secondaryPhone, e.email, e.crm, e.document, e.workingHourStart, e.workingHoursEnd)
			    FROM DoctorEntity e
			     WHERE e.document = :document
			""")
	Optional<Doctor> loadByDocument(String document);

	@Override
	default Long save(Doctor doctor) {
		var entity = DoctorEntity.valueOf(doctor);
		this.save(entity);
		return entity.getId();
	}

	@Override
	default void update(Doctor doctor) {
		var entity = DoctorEntity.valueOf(doctor);
		this.save(entity);
	}

	// TODO: IMPLEMENTAR
	@Override
	default List<DoctorSchedule> loadSchedule(Long doctorId, LocalDateTime start, LocalDateTime end) {
		return List.of();
	}
}
