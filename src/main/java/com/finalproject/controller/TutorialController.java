package com.finalproject.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.finalproject.entity.TutorialEntity;
import com.finalproject.service.TutorialService;

// handles all tutorial-related operations (CRUD)
@RestController
@RequestMapping("/api/tutorials")
@CrossOrigin(origins = "*")
public class TutorialController {

    private final TutorialService tutorialService;

    public TutorialController(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }

    // get all tutorials from database
    @GetMapping
    public List<TutorialEntity> getAllTutorials() {
        return tutorialService.getAllTutorials();
    }

    // get a single tutorial by id
    @GetMapping("/{id}")
    public ResponseEntity<TutorialEntity> getTutorialById(@PathVariable Integer id) {
        return tutorialService.getTutorialById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // create a new tutorial
    @PostMapping
    public TutorialEntity createTutorial(@RequestBody TutorialEntity tutorial) {
        return tutorialService.saveTutorial(tutorial);
    }

    // update an existing tutorial
    @PutMapping("/{id}")
    public ResponseEntity<TutorialEntity> updateTutorial(@PathVariable Integer id,
                                                         @RequestBody TutorialEntity tutorial) {
        try {
            return ResponseEntity.ok(tutorialService.updateTutorial(id, tutorial));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // delete a tutorial by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTutorial(@PathVariable Integer id) {
        tutorialService.deleteTutorial(id);
        return ResponseEntity.noContent().build();
    }
}