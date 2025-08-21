package com.hamsini.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hamsini.backend.model.EnrollRequest;
import com.hamsini.backend.repository.EnrollRepository;
 

@CrossOrigin(origins = "*") // Allow frontend requests
@RestController
@RequestMapping("/api/enroll")
public class EnrollController {
     @Autowired
    private EnrollRepository enrollRepo;

    @PostMapping
    public String enrollUser(@RequestBody EnrollRequest enroll) {
        enrollRepo.save(enroll);
        return "Enrollment Successful!";
    }
    
    // Get All Enrollments (for Admin Dashboard)
    @GetMapping
    public List<EnrollRequest> getAllEnrollments() {
        return enrollRepo.findAll();
    }
        
    }

