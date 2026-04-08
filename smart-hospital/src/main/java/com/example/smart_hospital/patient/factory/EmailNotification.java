package com.example.smart_hospital.patient.factory;

public class EmailNotification implements Notification {
    
    @Override
    public void send(String recipient, String message) {
        System.out.println("📧 Sending EMAIL to " + recipient + ": " + message);
    }
}