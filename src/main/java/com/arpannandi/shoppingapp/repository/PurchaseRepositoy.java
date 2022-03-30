package com.arpannandi.shoppingapp.repository;

import com.arpannandi.shoppingapp.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepositoy extends JpaRepository<Purchase,String> {
    List<Purchase> findByUserEmailOrderByDateDesc(String email);
}
