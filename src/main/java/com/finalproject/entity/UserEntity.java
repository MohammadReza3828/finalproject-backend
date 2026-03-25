package com.finalproject.entity;

import jakarta.persistence.*;

// represents the users table for authentication
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment primary key
    private Integer id;

    @Column(name = "username", unique = true, nullable = false) // must be unique for login
    private String username;

    @Column(name = "password", nullable = false) // stored as hashed password (BCrypt)
    private String password;

    @Column(name = "role", nullable = false) // defines user type (admin or customer)
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}