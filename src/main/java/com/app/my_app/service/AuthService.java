package com.app.my_app.service;

import com.app.my_app.CustomUserDetails;
import com.app.my_app.domain.User;
import com.app.my_app.repos.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public Long getCurrentUserId() {
        CustomUserDetails u = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return u.getUserId();
    }

    public User getCurrentUser() {
        return userRepository.findById(getCurrentUserId()).orElse(null);
    }
}