package com.arpannandi.shoppingapp.repository;

import com.arpannandi.shoppingapp.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal,String> {
}
