package com.example.appointments.application.domain.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record DoctorSchedule(Long doctorId, String doctorName, String speciality, LocalTime workingHourStart,
		LocalTime workingHourEnd, List<ScheduleBlock> scheduleBlocks) {

	public record ScheduleBlock(ScheduleBlockReason blockReason, LocalDateTime start, LocalDateTime end) {
	}

	public enum ScheduleBlockReason {
		APPOINTMENT, VACATION, NOT_WORKING_HOUR, OTHER
	}

}
