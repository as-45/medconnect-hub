package com.example.smart_hospital.patient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "login"; // loads login.html
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam(required = false) String username,
                               @RequestParam(required = false) String password) {
        // TODO: Implement authentication
        // For now, redirect to dashboard
        return "redirect:/dashboard";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@RequestParam(required = false) String email,
                                  @RequestParam(required = false) String password,
                                  @RequestParam(required = false) String role) {
        // TODO: Implement user registration
        // For now, redirect to login
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // TODO: Get actual patient data from session/database
        Map<String, Object> patient = new HashMap<>();
        patient.put("name", "John Doe");
        patient.put("email", "john@example.com");
        patient.put("phone", "+1-555-0100");
        
        model.addAttribute("patient", patient);
        model.addAttribute("totalAppointments", 5);
        model.addAttribute("upcomingAppointments", 2);
        model.addAttribute("pendingBills", 1);
        model.addAttribute("totalRecords", 8);
        
        return "patient/dashboard";
    }

    @GetMapping("/doctors")
    public String doctors(Model model,
                          @RequestParam(required = false) String name,
                          @RequestParam(required = false) String specialty) {
        // TODO: Get actual doctors from database
        Map<String, Object> patient = new HashMap<>();
        patient.put("name", "John Doe");
        
        model.addAttribute("patient", patient);
        model.addAttribute("searchName", name != null ? name : "");
        model.addAttribute("searchSpec", specialty != null ? specialty : "");
        model.addAttribute("doctors", new java.util.ArrayList<>()); // TODO: Fetch actual doctors
        
        return "patient/doctor-list";
    }

    @GetMapping("/book")
    public String bookAppointment(Model model) {
        // TODO: Get actual patient and doctor data
        Map<String, Object> patient = new HashMap<>();
        patient.put("name", "John Doe");
        
        model.addAttribute("patient", patient);
        model.addAttribute("doctor", new HashMap<>()); // TODO: Fetch actual doctor
        
        return "patient/book-appointment";
    }

    @GetMapping("/status")
    public String status(Model model) {
        // TODO: Get actual appointments
        Map<String, Object> patient = new HashMap<>();
        patient.put("name", "John Doe");
        
        model.addAttribute("patient", patient);
        model.addAttribute("appointments", new java.util.ArrayList<>()); // TODO: Fetch actual appointments
        
        return "patient/appointment-status";
    }
}