package com.arpannandi.shoppingapp.repository;

import com.arpannandi.shoppingapp.model.Deal;
import com.arpannandi.shoppingapp.model.Sex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal,String> {
    List<Deal> findByApparelGenderOrderByDealExpires(Sex gender);
}
