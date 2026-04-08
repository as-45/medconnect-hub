package com.example.smart_hospital.patient.factory;

public interface Notification {
    
    void send(String recipient, String message);
}