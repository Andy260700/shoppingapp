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

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
public class PurchaseController {
    PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService){
        this.purchaseService = purchaseService;
    }

    @PostMapping("/user/purchase")
    public ModelAndView purchaseItem(@RequestParam("apparelId") Long apparelId, @RequestParam("cost") Double cost, Principal principal){
        String name = principal.getName();
        purchaseService.addTransaction(name, cost, apparelId);
        return new ModelAndView("redirect:/");
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
}
