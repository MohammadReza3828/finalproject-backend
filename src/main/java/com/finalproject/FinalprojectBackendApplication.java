package com.finalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// main entry point of the Spring Boot application
@SpringBootApplication
public class FinalprojectBackendApplication {

    public static void main(String[] args) {
        // starts the application and initializes the backend server
        SpringApplication.run(FinalprojectBackendApplication.class, args);
    }
}