package com.example.smart_hospital.patient.factory;

public class NotificationFactory {
    
    public static Notification getNotification(String type) {
        if (type.equalsIgnoreCase("EMAIL")) {
            return new EmailNotification();
        } else if (type.equalsIgnoreCase("SMS")) {
            return new SmsNotification();
        }
        return new EmailNotification(); // Default
    }
}