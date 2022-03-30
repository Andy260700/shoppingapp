package com.arpannandi.shoppingapp.dto;

import com.arpannandi.shoppingapp.model.Season;
import com.arpannandi.shoppingapp.model.Sex;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class ApparelDto implements Serializable {
    private String name;
    private  double price;
    private  String category;
    private  String brand;
    private  LocalDateTime dateAdded;
    private  Season season;
    private  Sex gender;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApparelDto entity = (ApparelDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.price, entity.price) &&
                Objects.equals(this.category, entity.category) &&
                Objects.equals(this.brand, entity.brand) &&
                Objects.equals(this.dateAdded, entity.dateAdded) &&
                Objects.equals(this.season, entity.season) &&
                Objects.equals(this.gender, entity.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, category, brand, dateAdded, season, gender);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "price = " + price + ", " +
                "category = " + category + ", " +
                "brand = " + brand + ", " +
                "dateAdded = " + dateAdded + ", " +
                "season = " + season + ", " +
                "gender = " + gender + ")";
    }
}
