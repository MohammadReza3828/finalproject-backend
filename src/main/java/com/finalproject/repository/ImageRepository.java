package com.finalproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.entity.ImageEntity;

// repository for handling image database operations
public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {

    // find image by title (used to retrieve image for display)
    Optional<ImageEntity> findByTitle(String title);
}