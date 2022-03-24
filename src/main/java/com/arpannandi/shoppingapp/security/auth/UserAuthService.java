package com.arpannandi.shoppingapp.security.auth;

import com.arpannandi.shoppingapp.dto.UserDto;
import com.arpannandi.shoppingapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAuthService extends UserDetailsService {
    User saveUser(UserDto userDto);
}
