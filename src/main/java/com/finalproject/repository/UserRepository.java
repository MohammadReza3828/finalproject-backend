package com.finalproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.entity.UserEntity;

// repository for user-related database operations
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    // used during login to find user by username
    Optional<UserEntity> findByUsername(String username);
}