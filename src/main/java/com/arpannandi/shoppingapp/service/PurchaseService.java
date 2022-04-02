package com.arpannandi.shoppingapp.service;

import com.arpannandi.shoppingapp.model.Apparel;
import com.arpannandi.shoppingapp.model.Purchase;
import com.arpannandi.shoppingapp.model.User;
import com.arpannandi.shoppingapp.repository.ApparelRepository;
import com.arpannandi.shoppingapp.repository.PurchaseRepositoy;
import com.arpannandi.shoppingapp.repository.UserRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service("purchaseService")
public class PurchaseService {
    PurchaseRepositoy purchaseRepositoy;
    UserRepository userRepository;
    ApparelRepository apparelRepository;

    public PurchaseService(PurchaseRepositoy purchaseRepositoy, ApparelRepository apparelRepository, UserRepository userRepository){
        this.purchaseRepositoy = purchaseRepositoy;
        this.userRepository = userRepository;
        this.apparelRepository = apparelRepository;
    }

    public String addTransaction(String username, Double cost, Long apparelId, HttpSession httpSession){
        Apparel apparel = apparelRepository.findByApparelId(apparelId);
        User user = userRepository.findByEmail(username);
        Purchase purchase = new Purchase(LocalDateTime.now(), cost, apparel, user);
        purchase = purchaseRepositoy.save(purchase);

        HashSet<String> phrases = (HashSet<String>) httpSession.getAttribute("phrases");
        ArrayList<Double> prices = (ArrayList<Double>) httpSession.getAttribute("prices");

        for(String word : (apparel.getCategory() + " " + apparel.getBrand()).split(" "))
            phrases.add(word);
        prices.add(cost);

        httpSession.setAttribute("phrases", phrases);
        httpSession.setAttribute("prices", prices);

        return purchase.getPurchaseId().toString();
    }

    public List<Purchase> history(String username){
        List<Purchase> list = new ArrayList<>();
        list = purchaseRepositoy.findByUserEmailOrderByDateDesc(username);
        return list;
    }

    public void detailsToSession(Long apparelId, Double cost, HttpSession httpSession){
        Apparel apparel = apparelRepository.findByApparelId(apparelId);
        HashSet<String> phrases = (HashSet<String>) httpSession.getAttribute("phrases");
        ArrayList<Double> prices = (ArrayList<Double>) httpSession.getAttribute("prices");

        for(String word : (apparel.getCategory() + " " + apparel.getBrand()).split(" "))
            phrases.add(word);
        prices.add(cost);

        httpSession.setAttribute("phrases", phrases);
        httpSession.setAttribute("prices", prices);
    }

    public void loadHistoryToSession(String username, HttpSession httpSession){
        List<Purchase> list = new ArrayList<>();
        list = purchaseRepositoy.findByUserEmailOrderByDateDesc(username);

        HashSet<String> phrases = (HashSet<String>) httpSession.getAttribute("phrases");
        ArrayList<Double> prices = (ArrayList<Double>) httpSession.getAttribute("prices");

        for(Purchase purchase : list){
            for(String word : (purchase.getApparel().getCategory() + " " + purchase.getApparel().getBrand()).split(" "))
                phrases.add(word);
            prices.add(purchase.getPrice());
        }

        httpSession.setAttribute("phrases", phrases);
        httpSession.setAttribute("prices", prices);

    }
}
