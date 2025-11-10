package com.example.camerarent.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.camerarent.user.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public static class AuthRequest {
        @NotBlank
        public String email; // can be email or username, we store as username

        @NotBlank
        @Size(min = 6)
        public String password;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody AuthRequest body) {
        String username = body.email; // align with existing frontend

        if (userService.userExists(username)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "User already exists"));
        }

        userService.createUser(username, body.password);
        String token = jwtUtil.generateToken(username);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Signup successful",
                "token", token,
                "username", username
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest body) {
        String username = body.email;

        return userService.findByUsername(username)
                .filter(user -> userService.passwordMatches(body.password, user.getPassword()))
                .<ResponseEntity<?>>map(user -> {
                    String token = jwtUtil.generateToken(username);
                    return ResponseEntity.ok(Map.of(
                            "message", "Login successful",
                            "token", token,
                            "username", username
                    ));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("message", "Invalid credentials")));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // In a stateless JWT implementation, logout is handled on the client side
        // by removing the token. The server doesn't need to do anything.
        return ResponseEntity.ok(Map.of("message", "Logout successful"));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyToken() {
        // This endpoint can be used to verify if a token is valid
        return ResponseEntity.ok(Map.of("message", "Token is valid"));
    }
}


