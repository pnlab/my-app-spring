package com.app.my_app.rest;

import com.app.my_app.domain.User;
import com.app.my_app.model.UserDTO;
import com.app.my_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Transactional
public class AuthResource {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody UserDTO userDto) {
        if (isPasswordLengthInvalid(userDto.getPassword())) {
            return;
        }
        User user = userService.registerUser(userDto);
        System.out.println(userDto);
    }

    private static boolean isPasswordLengthInvalid(String password) {
        return password.length() < 4;
    }

}
