package com.finalproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.finalproject.entity.TutorialEntity;
import com.finalproject.repository.TutorialRepository;

// handles business logic for tutorials
@Service
public class TutorialService {

    private final TutorialRepository tutorialRepository;

    public TutorialService(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    // retrieve all tutorials from database
    public List<TutorialEntity> getAllTutorials() {
        return tutorialRepository.findAll();
    }

    // find a tutorial by id
    public Optional<TutorialEntity> getTutorialById(Integer id) {
        return tutorialRepository.findById(id);
    }

    // save a new tutorial
    public TutorialEntity saveTutorial(TutorialEntity tutorial) {
        return tutorialRepository.save(tutorial);
    }

    // update existing tutorial with new values
    public TutorialEntity updateTutorial(Integer id, TutorialEntity updatedTutorial) {
        return tutorialRepository.findById(id)
                .map(tutorial -> {
                    tutorial.setName(updatedTutorial.getName());
                    tutorial.setPrice(updatedTutorial.getPrice());
                    tutorial.setStatus(updatedTutorial.getStatus());
                    tutorial.setDescription(updatedTutorial.getDescription());
                    tutorial.setImage(updatedTutorial.getImage());
                    return tutorialRepository.save(tutorial);
                })
                .orElseThrow(() -> new RuntimeException("Tutorial not found with id: " + id));
    }

    // delete tutorial by id
    public void deleteTutorial(Integer id) {
        tutorialRepository.deleteById(id);
    }
}