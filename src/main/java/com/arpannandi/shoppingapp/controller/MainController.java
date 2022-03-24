package com.arpannandi.shoppingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/user/home")
    public String userHome(){
        return "home_user";
    }

    @GetMapping("/admin/panel")
    public String adminPanel(){
        return "admin_panel";
    }
}
