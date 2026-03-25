package com.finalproject.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.finalproject.entity.ImageEntity;
import com.finalproject.service.ImageService;

// handles image upload and retrieval
@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "*")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    // upload image with title and file
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(
            @RequestParam("title") String title,
            @RequestParam("file") MultipartFile file) {
        try {
            // save image to database via service
            ImageEntity savedImage = imageService.saveImage(title, file);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Image uploaded successfully");
            response.put("id", savedImage.getId());
            response.put("title", savedImage.getTitle());

            // return URL to access the uploaded image
            response.put("imageUrl", "http://172.20.10.5:8081/api/images/title/" + savedImage.getTitle());

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to upload image");
        }
    }

    // get all stored images
    @GetMapping
    public List<ImageEntity> getAllImages() {
        return imageService.getAllImages();
    }

    // retrieve image by id,returns binary data
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Integer id) {
        return imageService.getImageById(id)
                .map(image -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + image.getTitle() + "\"")
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(image.getPhoto()))
                .orElse(ResponseEntity.notFound().build());
    }

    // retrieve image by title,used by frontend to display images
    @GetMapping("/title/{title}")
    public ResponseEntity<byte[]> getImageByTitle(@PathVariable String title) {
        return imageService.getImageByTitle(title)
                .map(image -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + image.getTitle() + "\"")
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(image.getPhoto()))
                .orElse(ResponseEntity.notFound().build());
    }
}