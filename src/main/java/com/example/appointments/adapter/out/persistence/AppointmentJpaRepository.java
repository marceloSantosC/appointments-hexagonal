package com.example.appointments.adapter.out.persistence;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.appointments.adapter.out.persistence.model.AppointmentEntity;
import com.example.appointments.application.domain.model.Appointment;
import com.example.appointments.application.port.out.AppointmentPersistencePort;

public interface AppointmentJpaRepository extends JpaRepository<AppointmentEntity, Long>, AppointmentPersistencePort {

	@Override
	@Query(nativeQuery = true, value = """
			 SELECT CASE WHEN COUNT(a.ID) = 0 THEN TRUE ELSE FALSE END
			 FROM APPOINTMENT a
			 WHERE a.DOCTOR_ID = :doctorId
			   AND ((a.START BETWEEN :start AND :end) OR (a.ESTIMATED_END  BETWEEN :start AND :end))
			""")
	Optional<Boolean> isDoctorScheduleFree(Long doctorId, LocalDateTime start, LocalDateTime end);

	@Override
	default Long save(Appointment appointment) {
		var appointmentEntity = AppointmentEntity.valueOf(appointment);
		this.save(appointmentEntity);
		return appointmentEntity.getId();
	}
}
