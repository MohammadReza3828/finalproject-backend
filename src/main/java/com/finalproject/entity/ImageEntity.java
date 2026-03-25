package com.finalproject.entity;

import jakarta.persistence.*;

// represents the images table in the database
@Entity
@Table(name = "images")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment primary key
    private Integer id;

    @Column(name = "title")
    private String title;

    @Lob // used to store large binary data (image)
    @Column(name = "photo")
    private byte[] photo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}