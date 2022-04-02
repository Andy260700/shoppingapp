package com.arpannandi.shoppingapp.controller;

import com.arpannandi.shoppingapp.dto.ApparelDto;
import com.arpannandi.shoppingapp.dto.SearchDto;
import com.arpannandi.shoppingapp.model.Apparel;
import com.arpannandi.shoppingapp.service.ApparelService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Controller
public class SearchController {

    ApparelService apparelService;

    SearchController(ApparelService apparelService){
        this.apparelService = apparelService;
    }

    @ModelAttribute("searchObject")
    public SearchDto searchDto()
    {
        return new SearchDto();
    }

    @PostMapping("/search")
    public String searchFunc(Authentication authentication, Principal principal, Model model, @ModelAttribute("searchObject") SearchDto searchDto, HttpSession httpSession){
        if(httpSession.getAttribute("phrases")==null)
            httpSession.setAttribute("phrases", new HashSet<String>());
        if(httpSession.getAttribute("prices")==null) {
            httpSession.setAttribute("prices", new ArrayList<Double>());
        }

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

        List<Apparel> apparelList = apparelService.search(searchDto.getPhrase(), httpSession);
        model.addAttribute("apparelList", apparelList);

        model.addAttribute("username", name);
        model.addAttribute("role", role);
        return "search";
    }

    @GetMapping("/search")
    public String search(Authentication authentication, Principal principal, Model model, @RequestParam(value = "phrase", required = false) String phrase) {
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

        List<Apparel> apparelList = new ArrayList<>();
        model.addAttribute("apparelList", apparelList);

        model.addAttribute("username", name);
        model.addAttribute("role", role);
        return "search";
    }
}
