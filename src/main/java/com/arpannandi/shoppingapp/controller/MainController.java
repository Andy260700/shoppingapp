package com.arpannandi.shoppingapp.controller;

import com.arpannandi.shoppingapp.model.Apparel;
import com.arpannandi.shoppingapp.model.Deal;
import com.arpannandi.shoppingapp.repository.UserRepository;
import com.arpannandi.shoppingapp.service.ApparelService;
import com.arpannandi.shoppingapp.service.DealService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController {
    ApparelService apparelService;
    DealService dealService;

    public MainController(ApparelService apparelService, DealService dealService){
        this.apparelService = apparelService;
        this.dealService = dealService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home(Authentication authentication,Principal principal, Model model) {
        String name=null;
        String role=null;
        if(authentication != null) {
            Collection<? extends GrantedAuthority> granted = authentication.getAuthorities();
            if (granted != null) {
                role = granted.toArray()[0].toString();
            }
        }
        if (principal != null) {
            name = principal.getName();
        }
        List<Apparel> apparelList = apparelService.homePageList(name);
        model.addAttribute("apparelList", apparelList);
        model.addAttribute("username", name);
        model.addAttribute("role", role);
        return "home";
    }

    @GetMapping("/deals")
    public String deals(Authentication authentication,Principal principal, Model model) {
        String name=null;
        String role=null;
        if(authentication != null) {
            Collection<? extends GrantedAuthority> granted = authentication.getAuthorities();
            if (granted != null) {
                role = granted.toArray()[0].toString();
            }
        }
        if (principal != null) {
            name = principal.getName();
        }
        List<Deal> dealList = dealService.showDeals(name);
        model.addAttribute("dealList", dealList);
        model.addAttribute("username", name);
        model.addAttribute("role", role);
        return "deals";
    }

    @GetMapping("/new_arrivals")
    public String newArrivals(Authentication authentication,Principal principal, Model model) {
        String name=null;
        String role=null;
        if(authentication != null) {
            Collection<? extends GrantedAuthority> granted = authentication.getAuthorities();
            if (granted != null) {
                role = granted.toArray()[0].toString();
            }
        }
        if (principal != null) {
            name = principal.getName();
        }
        List<Apparel> apparelList = apparelService.arrivalPageList(name);
        model.addAttribute("apparelList", apparelList);
        model.addAttribute("username", name);
        model.addAttribute("role", role);
        return "new_arrivals";
    }

    @GetMapping("/seasonals")
    public String seasonals(Authentication authentication,Principal principal, Model model) {
        String name=null;
        String role=null;
        if(authentication != null) {
            Collection<? extends GrantedAuthority> granted = authentication.getAuthorities();
            if (granted != null) {
                role = granted.toArray()[0].toString();
            }
        }
        if (principal != null) {
            name = principal.getName();
        }
        List<Apparel> apparelList = apparelService.seasonalPageList(name);
        model.addAttribute("apparelList", apparelList);
        model.addAttribute("username", name);
        model.addAttribute("role", role);
        return "seasonals";
    }

}
