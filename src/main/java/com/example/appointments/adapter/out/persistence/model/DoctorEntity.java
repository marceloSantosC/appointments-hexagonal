package com.example.appointments.adapter.out.persistence.model;

import java.time.LocalTime;

import com.example.appointments.application.domain.model.Doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "DoctorEntity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Doctor")
public class DoctorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String speciality;

	private String phone;

	private String secondaryPhone;

	private String email;

	private String crm;

	private String document;

	private LocalTime workingHourStart;

	private LocalTime workingHoursEnd;

	public static DoctorEntity valueOf(Doctor doctor) {
		return DoctorEntity.builder().id(doctor.getId()).name(doctor.getName()).speciality(doctor.getSpeciality())
				.phone(doctor.getPhone()).secondaryPhone(doctor.getSecondaryPhone()).email(doctor.getEmail())
				.crm(doctor.getCrm()).document(doctor.getDocument()).workingHourStart(doctor.getWorkingHourStart())
				.workingHoursEnd(doctor.getWorkingHourEnd()).build();
	}

}
