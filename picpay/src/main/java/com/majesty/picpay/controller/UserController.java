package com.majesty.picpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.majesty.picpay.domain.user.User;
import com.majesty.picpay.dto.UserDTO;
import com.majesty.picpay.service.UserService;

@Controller("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(UserDTO user) {

        User newUser = userService.createUser(user);
        return null;

    }

}
