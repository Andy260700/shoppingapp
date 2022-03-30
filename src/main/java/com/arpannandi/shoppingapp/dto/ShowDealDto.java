package com.arpannandi.shoppingapp.dto;

import com.arpannandi.shoppingapp.model.Season;
import com.arpannandi.shoppingapp.model.Sex;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class ShowDealDto implements Serializable {
    private String name;
    private  double price;
    private  String category;
    private  String brand;
    private double discountedPrice;
    private LocalDateTime dealEnds;
    private Season season;
    private Sex gender;

    public ShowDealDto(String name, double price, String category, String brand, double discountedPrice, LocalDateTime dealEnds, Season season, Sex gender) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.discountedPrice = discountedPrice;
        this.dealEnds = dealEnds;
        this.season = season;
        this.gender = gender;
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

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public LocalDateTime getDealEnds() {
        return dealEnds;
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

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public void setDealEnds(LocalDateTime dealEnds) {
        this.dealEnds = dealEnds;
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
        if (!(o instanceof ShowDealDto)) return false;
        ShowDealDto that = (ShowDealDto) o;
        return Double.compare(that.price, price) == 0 && Double.compare(that.discountedPrice, discountedPrice) == 0 && name.equals(that.name) && category.equals(that.category) && brand.equals(that.brand) && dealEnds.equals(that.dealEnds) && season == that.season && gender == that.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, category, brand, discountedPrice, dealEnds, season, gender);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShowDealDto{");
        sb.append("name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append(", category='").append(category).append('\'');
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", discountedPrice=").append(discountedPrice);
        sb.append(", dealEnds=").append(dealEnds);
        sb.append(", season=").append(season);
        sb.append(", gender=").append(gender);
        sb.append('}');
        return sb.toString();
    }
}
