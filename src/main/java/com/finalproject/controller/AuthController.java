package com.finalproject.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.finalproject.entity.UserEntity;
import com.finalproject.service.UserService;

// handles authentication-related requests (login & register)
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // allows requests from frontend
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // register a new user
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        try {
            String username = request.get("username");
            String password = request.get("password");
            String role = request.get("role");

            // create user through service (password will be hashed there)
            UserEntity user = userService.createUser(username, password, role);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "User created successfully");
            response.put("id", user.getId());
            response.put("username", user.getUsername());
            response.put("role", user.getRole());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create user");
        }
    }

    // login and validate user credentials
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String role = request.get("role");

        Optional<UserEntity> userOptional = userService.login(username, password, role);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("id", user.getId());
            response.put("username", user.getUsername());
            response.put("role", user.getRole());

            return ResponseEntity.ok(response);
        }

        // return 401 if credentials are invalid
        return ResponseEntity.status(401).body("Invalid username, password, or role");
    }
}