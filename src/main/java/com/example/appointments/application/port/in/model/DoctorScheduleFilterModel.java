package com.example.appointments.application.port.in.model;

import java.time.LocalDateTime;

public record DoctorScheduleFilterModel(Long doctorId, LocalDateTime start, LocalDateTime end) {
}
