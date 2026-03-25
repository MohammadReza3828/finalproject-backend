package com.finalproject.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.finalproject.entity.UserEntity;
import com.finalproject.repository.UserRepository;

// handles user authentication and security logic
@Service
public class UserService {

    private final UserRepository userRepository;

    // used for hashing and verifying passwords
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // create a new user and hash the password before saving
    public UserEntity createUser(String username, String rawPassword, String role) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword)); // hash password
        user.setRole(role);
        return userRepository.save(user);
    }

    // validate login credentials
    public Optional<UserEntity> login(String username, String rawPassword, String role) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            // check hashed password and role
            boolean passwordMatches = passwordEncoder.matches(rawPassword, user.getPassword());
            boolean roleMatches = user.getRole().equalsIgnoreCase(role);

            if (passwordMatches && roleMatches) {
                return Optional.of(user);
            }
        }

        return Optional.empty(); // return empty if login fails
    }
}