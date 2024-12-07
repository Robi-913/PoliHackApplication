package com.example.PoliHack.controller;

import com.example.PoliHack.model.User;
import com.example.PoliHack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")  // Added base path
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")  // Changed endpoint to '/api/users/add'
    public void addUser(@RequestBody User user) {
        userService.saveUser(user);
    }
}
