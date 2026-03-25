package com.finalproject.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.finalproject.entity.ImageEntity;
import com.finalproject.repository.ImageRepository;

// handles business logic for images
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    // save uploaded image into database as binary data
    public ImageEntity saveImage(String title, MultipartFile file) throws IOException {
        ImageEntity image = new ImageEntity();
        image.setTitle(title);
        image.setPhoto(file.getBytes()); // convert file to byte array
        return imageRepository.save(image);
    }

    // get all images
    public List<ImageEntity> getAllImages() {
        return imageRepository.findAll();
    }

    // find image by id
    public Optional<ImageEntity> getImageById(Integer id) {
        return imageRepository.findById(id);
    }

    // find image by title (used when displaying images in frontend)
    public Optional<ImageEntity> getImageByTitle(String title) {
        return imageRepository.findByTitle(title);
    }
}