package com.Vakul.userservice.controller;

import com.Vakul.userservice.model.User;
import com.Vakul.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("/add_user")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }
}
