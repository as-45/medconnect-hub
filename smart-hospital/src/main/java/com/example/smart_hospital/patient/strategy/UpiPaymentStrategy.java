package com.example.smart_hospital.patient.strategy;

public class UpiPaymentStrategy implements PaymentStrategy {
    
    @Override
    public boolean pay(double amount) {
        System.out.println("📱 Paid ₹" + amount + " using UPI");
        return true;
    }
}