package com.arpannandi.shoppingapp.service;

import com.arpannandi.shoppingapp.dto.ApparelDto;
import com.arpannandi.shoppingapp.model.Apparel;
import com.arpannandi.shoppingapp.model.Season;
import com.arpannandi.shoppingapp.model.User;
import com.arpannandi.shoppingapp.model.UserPreference;
import com.arpannandi.shoppingapp.repository.ApparelRepository;
import com.arpannandi.shoppingapp.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("apparelService")
public class ApparelService {
    private final ApparelRepository apparelRepository;
    private final UserRepository userRepository;

    public ApparelService(ApparelRepository apparelRepository, UserRepository userRepository){
        this.apparelRepository = apparelRepository;
        this.userRepository = userRepository;
    }

    public String addApparel(ApparelDto apparelDto){
        Apparel apparel = new Apparel(apparelDto.getName(), apparelDto.getPrice(), apparelDto.getCategory(), apparelDto.getBrand(),
                LocalDateTime.now(), apparelDto.getSeason(), apparelDto.getGender());
        apparel = apparelRepository.save(apparel);
        return apparel.getApparelId().toString();
    }

    public List<Apparel> search(String phrase){
        if(phrase==null)
            return new ArrayList<>();

        List<Apparel> apparelList = apparelRepository.findByNameContaining(phrase);
        apparelList.addAll(apparelRepository.findByCategoryContaining(phrase));
        apparelList.addAll(apparelRepository.findByBrand(phrase));

        return apparelList;
    }

    public List<Apparel> homePageList(String username){
        List<Apparel> apparelList = new ArrayList<>();
        if(username==null){
            apparelList = apparelRepository.findAll();
        }
        else{
            User user = userRepository.findByEmail(username);
            if(user.getPreference()== UserPreference.NEW_ARRIVAL)
                apparelList = apparelRepository.findByGenderOrderByDateAddedDesc(user.getGender());
            else
            {
                Season season = Season.SUMMER;
                apparelList = apparelRepository.findByGenderAndSeason(user.getGender(), season);
            }
        }
        return apparelList;
    }

    public List<Apparel> seasonalPageList(String username){
        List<Apparel> apparelList = new ArrayList<>();
        Season season = Season.SUMMER;
        if(username==null){
            apparelList = apparelRepository.findBySeason(season);
        }
        else{
            User user = userRepository.findByEmail(username);
            apparelList = apparelRepository.findByGenderAndSeason(user.getGender(), season);
        }
        return apparelList;
    }

    public List<Apparel> arrivalPageList(String username){
        List<Apparel> apparelList = new ArrayList<>();
        if(username==null){
            apparelList = apparelRepository.findAll(Sort.by(Sort.Direction.DESC, "dateAdded"));
        }
        else{
            User user = userRepository.findByEmail(username);
            apparelList = apparelRepository.findByGenderOrderByDateAddedDesc(user.getGender());
        }
        return apparelList;
    }

}
