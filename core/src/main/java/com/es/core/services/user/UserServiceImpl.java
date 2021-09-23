package com.es.core.services.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserDetailsManager userDetailsManager;

    @Override
    public String getUsername() {
        if (isAuthenticated()) {
            return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        } else {
            return null;
        }
    }

    @Override
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication != null) && !authentication.getPrincipal().equals("anonymousUser");
    }

    @Override
    public void signUp(String username, String password) {
        UserDetails user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();
        userDetailsManager.createUser(user);
    }
}
