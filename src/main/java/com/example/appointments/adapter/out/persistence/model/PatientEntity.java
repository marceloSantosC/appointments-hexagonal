package com.example.appointments.adapter.out.persistence.model;

import java.time.LocalDate;

import com.example.appointments.application.domain.model.Patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Patient")
public class PatientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String document;

	private LocalDate birthDate;

	private String history;

	private String phone;

	private String secondaryPhone;

	private String email;

	public static PatientEntity valueOf(Patient patient) {
		return PatientEntity.builder().id(patient.getId()).name(patient.getName()).document(patient.getDocument())
				.birthDate(patient.getBirthDate()).history(patient.getHistory()).phone(patient.getPhone())
				.secondaryPhone(patient.getSecondaryPhone()).email(patient.getEmail()).build();
	}

}
