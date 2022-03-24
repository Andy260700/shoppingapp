package com.arpannandi.shoppingapp.security.auth;

import com.arpannandi.shoppingapp.dto.UserDto;
import com.arpannandi.shoppingapp.model.Role;
import com.arpannandi.shoppingapp.model.User;
import com.arpannandi.shoppingapp.repository.RoleRepository;
import com.arpannandi.shoppingapp.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserAuthServiceImpl implements UserAuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public UserAuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder();
        this.roleRepository = roleRepository;
    }

    @Override
    public User saveUser(UserDto userDto){

        Role role = Optional.ofNullable(roleRepository.findByName(userDto.getRole())).orElse(new Role(userDto.getRole()));

        User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                userDto.getName(), userDto.getPreference(), userDto.getGender(), userDto.getContact(), role);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(Collections.singleton(user.getRole())));
    }

    private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
