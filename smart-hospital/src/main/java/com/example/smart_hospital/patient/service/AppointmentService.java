package com.example.smart_hospital.patient.service;

import com.example.smart_hospital.patient.model.Appointment;
import com.example.smart_hospital.patient.model.Patient;
import com.example.smart_hospital.patient.repository.AppointmentRepository;
import com.example.smart_hospital.patient.repository.PatientRepository;
import com.example.smart_hospital.patient.factory.NotificationFactory;
import com.example.smart_hospital.patient.factory.Notification;
import com.example.smart_hospital.patient.strategy.PaymentStrategy;
import com.example.smart_hospital.patient.strategy.CardPaymentStrategy;
import com.example.smart_hospital.patient.strategy.UpiPaymentStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
    public Appointment bookAppointment(String patientId, String doctorId, 
                                       LocalDateTime time, 
                                       String reason, String paymentType) {
        
        // 1. Find Patient (MongoDB ID is String)
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        // 2. Check availability (optional)
        List<Appointment> conflicts = appointmentRepository
            .findByDoctorIdAndAppointmentTime(doctorId, time);
        if (!conflicts.isEmpty()) {
            throw new RuntimeException("Slot already booked");
        }
        
        // 3. Create Appointment
        Appointment appointment = new Appointment();
        appointment.setPatientId(patientId);  // ✅ Store as String reference
        appointment.setDoctorId(doctorId);
        appointment.setAppointmentTime(time);
        appointment.setReason(reason != null ? reason : "General Consultation");
        appointment.setStatus("CONFIRMED");
        
        // 4. Process Payment (Strategy Pattern - unchanged)
        PaymentStrategy strategy = paymentType.equalsIgnoreCase("UPI") 
            ? new UpiPaymentStrategy() 
            : new CardPaymentStrategy();
        strategy.pay(500.00);
        
        // 5. Save to MongoDB
        Appointment saved = appointmentRepository.save(appointment);
        
        // 6. Send Notification (Factory Pattern - unchanged)
        Notification notify = NotificationFactory.getNotification("EMAIL");
        notify.send(patient.getEmail(), "Appointment Confirmed for " + time);
        
        return saved;
    }
    
    public List<Appointment> getAppointmentsByPatient(String patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }
}