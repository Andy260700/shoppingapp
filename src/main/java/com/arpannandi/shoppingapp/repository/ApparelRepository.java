package com.arpannandi.shoppingapp.repository;

import com.arpannandi.shoppingapp.model.Apparel;
import com.arpannandi.shoppingapp.model.Season;
import com.arpannandi.shoppingapp.model.Sex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ApparelRepository extends JpaRepository<Apparel,String> {
    List<Apparel> findAll();
    Apparel findByApparelId(Long apparelId);
    List<Apparel> findByNameContaining(String infix);
    List<Apparel> findByCategoryContaining(String infix);
    List<Apparel> findByBrand(String brand);
    List<Apparel> findByGenderOrderByDateAddedDesc(Sex gender);
    List<Apparel> findBySeason(Season season);
    List<Apparel> findByGenderAndSeason(Sex gender, Season season);
}
