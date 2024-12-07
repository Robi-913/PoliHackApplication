package com.example.PoliHack.controller;

import com.example.PoliHack.model.User;
import com.example.PoliHack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public boolean loginUser(@RequestBody User user) {
        System.out.println("Received login request for nickname: " + user.getNickname());
        return userService.authenticateUser(user.getNickname(), user.getPassword());
    }

    @PostMapping("/register")
    public boolean registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }


}
