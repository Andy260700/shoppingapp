package com.arpannandi.shoppingapp.service;

import com.arpannandi.shoppingapp.dto.DealDto;
import com.arpannandi.shoppingapp.dto.ShowDealDto;
import com.arpannandi.shoppingapp.model.Apparel;
import com.arpannandi.shoppingapp.model.Deal;
import com.arpannandi.shoppingapp.model.User;
import com.arpannandi.shoppingapp.repository.ApparelRepository;
import com.arpannandi.shoppingapp.repository.DealRepository;
import com.arpannandi.shoppingapp.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service("dealService")
public class DealService {
    private final ApparelRepository apparelRepository;
    private final UserRepository userRepository;
    private final DealRepository dealRepository;

    public DealService(ApparelRepository apparelRepository, UserRepository userRepository, DealRepository dealRepository) {
        this.apparelRepository = apparelRepository;
        this.userRepository = userRepository;
        this.dealRepository = dealRepository;
    }

    public String addDeal(DealDto dealDto){
        String res = "";
        Apparel apparel = apparelRepository.findByApparelId(dealDto.getApparelId());
        if(apparel!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            Deal deal = new Deal(apparel, dealDto.getDiscountPrice(), LocalDateTime.parse(dealDto.getDealExpires(), formatter));
            deal = dealRepository.save(deal);
            res = deal.getDealId().toString();
        }
        return res;
    }

    public List<Deal> showDeals(String username){
        List<Deal> deals=null;
        if(username==null){
            deals = dealRepository.findAll(Sort.by(Sort.Direction.ASC, "dealExpires"));
        }
        else{
            User user = userRepository.findByEmail(username);
            deals = dealRepository.findByApparelGenderOrderByDealExpires(user.getGender());
        }
        return deals;
    }
}
