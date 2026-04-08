package com.example.smart_hospital.patient.strategy;

public class CardPaymentStrategy implements PaymentStrategy {
    
    @Override
    public boolean pay(double amount) {
        System.out.println("💳 Paid ₹" + amount + " using Credit/Debit Card");
        return true;
    }
}