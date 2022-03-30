package com.arpannandi.shoppingapp.controller;

import com.arpannandi.shoppingapp.dto.ApparelDto;
import com.arpannandi.shoppingapp.dto.DealDto;
import com.arpannandi.shoppingapp.dto.UserDto;
import com.arpannandi.shoppingapp.model.Deal;
import com.arpannandi.shoppingapp.service.ApparelService;
import com.arpannandi.shoppingapp.service.DealService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Collection;

@Controller
public class AdminController {
    ApparelService apparelService;
    DealService dealService;

    AdminController(ApparelService apparelService, DealService dealService){
        this.apparelService = apparelService;
        this.dealService = dealService;
    }

    @ModelAttribute("apparel")
    public ApparelDto apparelDto()
    {
        return new ApparelDto();
    }

    @ModelAttribute("deal")
    public DealDto dealDto()
    {
        return new DealDto();
    }

    @GetMapping("/admin/")
    public String adminPanel(Authentication authentication, Principal principal, Model model) {
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
        model.addAttribute("username", name);
        model.addAttribute("role", role);
        return "admin_panel";
    }

    @PostMapping("/admin/addapparel")
    public ModelAndView addApparel(@ModelAttribute("apparel") ApparelDto apparelDto, RedirectAttributes attributes){
        String id = apparelService.addApparel(apparelDto);
        attributes.addAttribute("apparel_id", id);
        return new ModelAndView("redirect:/admin/");
    }

    @PostMapping("/admin/adddeal")
    public  ModelAndView addDeal(@ModelAttribute("deal") DealDto dealDto, RedirectAttributes attributes){
        String id = dealService.addDeal(dealDto);
        attributes.addAttribute("deal_id", id);
        return new ModelAndView("redirect:/admin/");
    }

}
