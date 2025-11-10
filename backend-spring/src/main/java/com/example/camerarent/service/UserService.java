package com.example.camerarent.service;

public class UserService {

    public void signup(String username, String password) {
        validateSignupInput(username, password);
        // Additional signup logic (e.g., saving user to the database) goes here
    }

    private void validateSignupInput(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
    }
}