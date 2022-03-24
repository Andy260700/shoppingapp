package com.arpannandi.shoppingapp.repository;

import com.arpannandi.shoppingapp.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepositoy extends JpaRepository<Purchase,String> {
}
