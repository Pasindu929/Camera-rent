package com.example.camerarent.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @GetMapping
    public ResponseEntity<?> getDashboardData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        return ResponseEntity.ok(Map.of(
                "message", "Welcome to the dashboard",
                "username", username,
                "data", Map.of(
                        "totalCameras", 25,
                        "availableCameras", 18,
                        "rentedCameras", 7,
                        "totalRevenue", 12500.00
                )
        ));
    }
}
