package com.example.smart_hospital.patient.repository;

import com.example.smart_hospital.patient.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {  // ✅ String ID
    
    // ✅ MongoDB query methods (same as JPA!)
    Optional<Patient> findByEmail(String email);
    
    // ✅ Custom query with @Query if needed
    // @Query("{ 'email': ?0 }")
    // Patient findByEmailCustom(String email);
}