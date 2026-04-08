package com.example.smart_hospital.patient.repository;

import com.example.smart_hospital.patient.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    
    // ✅ Find appointments by patient ID
    List<Appointment> findByPatientId(String patientId);
    
    // ✅ Find by doctor and time (for availability check)
    List<Appointment> findByDoctorIdAndAppointmentTime(String doctorId, LocalDateTime time);
}