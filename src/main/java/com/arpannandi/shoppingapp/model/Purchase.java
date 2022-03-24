package com.arpannandi.shoppingapp.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Previous_purchases")
public class Purchase {
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long purchaseId;

    @Column(name="date", nullable = false)
    private LocalDateTime date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apparel_id")
    private Apparel apparel;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Purchase(){}

    public Purchase(LocalDateTime date, Apparel apparel, User user) {
        this.date = date;
        this.apparel = apparel;
        this.user = user;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Apparel getApparel() {
        return apparel;
    }

    public User getUser() {
        return user;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setApparel(Apparel apparel) {
        this.apparel = apparel;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

