package com.arpannandi.shoppingapp.security.auth;

import com.arpannandi.shoppingapp.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserAuthController {
    private UserAuthService userAuthService;

    public UserAuthController(UserAuthService userAuthService){
        this.userAuthService = userAuthService;
    }

    @ModelAttribute("user")
    public UserDto userDto()
    {
        return new UserDto();
    }

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") UserDto userDto){
        System.out.println(userDto.toString());
        userAuthService.saveUser(userDto);
        return "redirect:/registration?success";
    }
}
