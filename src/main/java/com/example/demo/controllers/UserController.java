package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private static final String path = "/users";

    @Autowired
    UserRepository repository;

    //
    @PostMapping(path + "/add")
    public User addUser(@RequestBody User user) {
        return repository.addUser(user);
    }

    @PutMapping(path + "/update")
    public User updateUser(@RequestBody User user) {
        return repository.updateUser(user);
    }


}
