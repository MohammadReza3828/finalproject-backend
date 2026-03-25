package com.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.finalproject.entity.TutorialEntity;

// repository for tutorial CRUD operations (handled automatically by JPA)
public interface TutorialRepository extends JpaRepository<TutorialEntity, Integer> {
}