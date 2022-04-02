package com.arpannandi.shoppingapp.controller;

import com.arpannandi.shoppingapp.model.Purchase;
import com.arpannandi.shoppingapp.repository.PurchaseRepositoy;
import com.arpannandi.shoppingapp.service.PurchaseService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Controller
public class PurchaseController {
    PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService){
        this.purchaseService = purchaseService;
    }

    @PostMapping("/user/purchase")
    public ModelAndView purchaseItem(@RequestParam("apparelId") Long apparelId, @RequestParam("cost") Double cost, Principal principal, HttpSession httpSession){
        if(httpSession.getAttribute("phrases")==null)
            httpSession.setAttribute("phrases", new HashSet<String>());
        if(httpSession.getAttribute("prices")==null) {
            httpSession.setAttribute("prices", new ArrayList<Double>());
        }

        String name = principal.getName();
        purchaseService.addTransaction(name, cost, apparelId, httpSession);
        return new ModelAndView("redirect:/user/history");
    }

    @GetMapping("/user/history")
    public String purchaseHistory(Model model, Principal principal, Authentication authentication){
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

        List<Purchase> purchases = purchaseService.history(name);
        model.addAttribute("purchaseList", purchases);

        model.addAttribute("username", name);
        model.addAttribute("role", role);

        return "history";
    }

    @PostMapping("/details")
    public ModelAndView showDetails(HttpSession httpSession, @RequestParam("apparelId") Long apparelId, @RequestParam("cost") Double cost){
        if(httpSession.getAttribute("phrases")==null)
            httpSession.setAttribute("phrases", new HashSet<String>());
        if(httpSession.getAttribute("prices")==null) {
            httpSession.setAttribute("prices", new ArrayList<Double>());
        }
        purchaseService.detailsToSession(apparelId,cost,httpSession);

        return new ModelAndView("redirect:/");
    }

}
