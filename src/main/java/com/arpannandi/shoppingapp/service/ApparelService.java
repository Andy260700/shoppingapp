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
import java.lang.Math;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
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

    public List<Apparel> search(String phrase, HttpSession httpSession){
        if(phrase==null)
            return new ArrayList<>();

        HashSet<String> phrases = (HashSet<String>) httpSession.getAttribute("phrases");
        phrases.add(phrase);
        httpSession.setAttribute("phrases",phrases);

        HashSet<Apparel> apparelList = apparelRepository.findByNameContaining(phrase);
        apparelList.addAll(apparelRepository.findByCategoryContaining(phrase));
        apparelList.addAll(apparelRepository.findByBrand(phrase));

        ArrayList<Apparel> apparelArrayList = new ArrayList<>(apparelList);

        return apparelArrayList;
    }

    public List<Apparel> homePageList(String username, HttpSession httpSession){
        List<Apparel> apparelList = new ArrayList<>();
        HashSet<String> phrases = (HashSet<String>) httpSession.getAttribute("phrases");
        ArrayList<Double> prices = (ArrayList<Double>) httpSession.getAttribute("prices");

        double mean = 1500.0 ;
        double variance = 1000.0; //standard deviation


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

        List<Apparel> resList = new ArrayList<>();
        if(prices.size()>=5){
            double sum = 0;
            double sd_sum = 0;
            for(double d : prices)
                sum += d;

            mean = sum/prices.size();

            for(double d: prices)
                sd_sum += ((d-mean)*(d-mean));

            variance = Math.sqrt(sd_sum/prices.size()) ;
        }

        if(!phrases.isEmpty()) {
//            for (String word : phrases)
//                System.out.println(word);

            for (Apparel apparel : apparelList) {
                String temp = apparel.getBrand() + " " + apparel.getName() + " " + apparel.getCategory();
//                System.out.println(temp);
//                System.out.println(mean);
//                System.out.println(variance);
//                System.out.println(apparel.getPrice());

                if (apparel.getPrice() > (mean-variance) && apparel.getPrice() < (mean+variance)) {
                    for (String word : phrases) {
                        if (temp.contains(word)) {
//                            System.out.println("====");
                            resList.add(apparel);
                            break;
                        }
                    }
                }
            }
        }
        else{
            for(Apparel apparel : apparelList){
                if (apparel.getPrice() > (mean-variance) && apparel.getPrice() < (mean+variance))
                    resList.add(apparel);
            }
        }

        return resList;
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
