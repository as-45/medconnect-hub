package com.example.smart_hospital.patient.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;

@Document(collection = "appointments")
@Data
public class Appointment {
    
    @Id
    private String id;
    
    // ✅ Store patient reference as embedded object or just ID
    private String patientId;  // Reference to Patient._id
    
    private String doctorId;
    private LocalDateTime appointmentTime;
    private String status;  // PENDING, CONFIRMED, CANCELLED
    private String reason;
    
    // ✅ Optional: Embed patient info directly (denormalization)
    // private Patient patient;  // Uncomment if you want embedded docs
}