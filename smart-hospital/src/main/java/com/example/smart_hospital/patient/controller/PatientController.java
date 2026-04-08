package com.example.smart_hospital.patient.controller;
import com.example.smart_hospital.patient.model.Appointment;
import com.example.smart_hospital.patient.model.Patient;  
import com.example.smart_hospital.patient.service.AppointmentService;  
import com.example.smart_hospital.patient.repository.PatientRepository;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;  // ✅ Added

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    
    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired  // ✅ Added: Inject repository for findByEmail
    private PatientRepository patientRepository;
    
    @PostMapping("/book")
    public Appointment bookAppointment(
            @RequestParam String patientId,
            @RequestParam String doctorId,
            @RequestParam String time,
            @RequestParam(required = false) String reason,
            @RequestParam(defaultValue = "CARD") String paymentType) {
        
        return appointmentService.bookAppointment(
            patientId,
            doctorId,
            LocalDateTime.parse(time),
            reason,
            paymentType
        );
    }
    
    @GetMapping("/appointments")
    public List<Appointment> getAppointments(@RequestParam String patientId) {
        return appointmentService.getAppointmentsByPatient(patientId);
    }
    
    // ✅ FIXED: Fully working findByEmail endpoint
    @GetMapping("/findByEmail")
    public Object findByEmail(@RequestParam String email) {
        Optional<Patient> patient = patientRepository.findByEmail(email);
        
        if (patient.isPresent()) {
            return patient.get();
        } else {
            return "Patient not found with email: " + email;
        }
    }
}