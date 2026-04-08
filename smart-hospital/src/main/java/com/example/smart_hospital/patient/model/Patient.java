package com.example.smart_hospital.patient.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;
import java.util.List;

@Document(collection = "patients")  // ✅ MongoDB annotation (not @Entity)
@Data
public class Patient {
    
    @Id  // ✅ MongoDB ID (not @GeneratedValue)
    private String id;  // ✅ MongoDB uses String IDs by default
    
    @Field("full_name")  // Optional: custom field names
    private String fullName;
    
    private String email;
    private String password;
    private String phone;
    private String address;
    
    // ✅ Embedded documents (no @OneToMany needed in MongoDB!)
    private List<Appointment> appointments;
}