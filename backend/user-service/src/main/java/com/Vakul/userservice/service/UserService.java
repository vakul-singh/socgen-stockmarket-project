package com.Vakul.userservice.service;

import com.Vakul.userservice.model.User;
import com.Vakul.userservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public void addUser(User user){
        userRepo.save(user);
    }

}