package com.example.demo.repositories;

import com.example.demo.models.User;


public interface UserRepositoryInterface {

    public User addUser(User user);

    public User updateUser(User user);

    public User getUserById(String userId);
}
