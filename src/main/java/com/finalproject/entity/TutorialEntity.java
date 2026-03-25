package com.finalproject.entity;

import jakarta.persistence.*;

// represents the tutorials table in the database
@Entity
@Table(name = "tutorials")
public class TutorialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-generated primary key
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @Column(name = "image") // stores reference to image (e.g. title or path)
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}