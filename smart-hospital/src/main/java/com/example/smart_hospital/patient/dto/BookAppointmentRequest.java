package com.example.smart_hospital.patient.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookAppointmentRequest {
    
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentTime;
    private String reason;
    private String paymentType; // CARD or UPI
}