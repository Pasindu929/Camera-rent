package com.example.camerarent.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void signup_shouldThrowExceptionForInvalidInput() {
        // Arrange
        UserService userService = new UserService();

        // Act & Assert
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            userService.signup("", "password123"); // Empty username
        });
        assertEquals("Username cannot be empty", exception1.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            userService.signup("username", ""); // Empty password
        });
        assertEquals("Password cannot be empty", exception2.getMessage());

        Exception exception3 = assertThrows(IllegalArgumentException.class, () -> {
            userService.signup("username", "short"); // Weak password
        });
        assertEquals("Password must be at least 8 characters long", exception3.getMessage());
    }
}