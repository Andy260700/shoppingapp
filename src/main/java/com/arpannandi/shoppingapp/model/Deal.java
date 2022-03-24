package com.arpannandi.shoppingapp.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Deals")
public class Deal {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long dealId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apparel_id")
    private Apparel apparel;

    @Column(name = "discount_price", nullable = false)
    private double discountPrice;

    @Column(name="deal_expires", nullable = false)
    private LocalDateTime dealExpires;

    public Deal(Apparel apparel, double discountPrice, LocalDateTime dealExpires) {
        this.apparel = apparel;
        this.discountPrice = discountPrice;
        this.dealExpires = dealExpires;
    }

    public Deal(){}

    public Long getDealId() {
        return dealId;
    }

    public Apparel getApparel() {
        return apparel;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public LocalDateTime getDealExpires() {
        return dealExpires;
    }

    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    public void setApparel(Apparel apparel) {
        this.apparel = apparel;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void setDealExpires(LocalDateTime dealExpires) {
        this.dealExpires = dealExpires;
    }
}

