package com.arpannandi.shoppingapp.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class DealDto implements Serializable {
    private double discountPrice;
    private  String dealExpires;
    private Long apparelId;


    public double getDiscountPrice() {
        return discountPrice;
    }

    public String getDealExpires() {
        return dealExpires;
    }

    public Long getApparelId() { return apparelId; }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void setDealExpires(String dealExpires) {
        this.dealExpires = dealExpires;
    }

    public void setApparelId(Long apparelId) {
        this.apparelId = apparelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DealDto entity = (DealDto) o;
        return Objects.equals(this.discountPrice, entity.discountPrice) &&
                Objects.equals(this.apparelId, entity.apparelId) &&
                Objects.equals(this.dealExpires, entity.dealExpires);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountPrice, dealExpires, apparelId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "discountPrice = " + discountPrice + ", " +
                "apparel = " + apparelId + ", " +
                "dealExpires = " + dealExpires + ")";
    }
}
