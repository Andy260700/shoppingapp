package com.arpannandi.shoppingapp.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Apparels")
public class Apparel {
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long apparelId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="price", nullable = false)
    private double price;

    //represents a generic name of the apparel, meant for ease of searching.
    @Column(name="category", nullable = false)
    private String category;

    @Column(name="brand")
    private String brand;

    @Column(name="inclusion_date", nullable = false)
    private LocalDateTime dateAdded;

    @Column(name="season", nullable = false)
    @Enumerated(EnumType.STRING)
    private Season season;

    @Column(name="gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex gender;

    public Apparel(){}

    public Apparel(String name, double price, String category, String brand, LocalDateTime dateAdded, Season season, Sex gender) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.dateAdded = dateAdded;
        this.season = season;
        this.gender = gender;
    }

    public Long getApparelId() {
        return apparelId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public Season getSeason() {
        return season;
    }

    public Sex getGender() {
        return gender;
    }

    public void setApparelId(Long apparelIdd) {
        this.apparelId = apparelId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }
}

