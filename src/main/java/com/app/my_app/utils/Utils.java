package com.app.my_app.utils;

import com.app.my_app.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {
    public CustomUserDetails getUser(){
        CustomUserDetails u = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return u;
    }
}
