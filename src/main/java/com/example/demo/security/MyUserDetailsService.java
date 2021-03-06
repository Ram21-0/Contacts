package com.example.demo.security;

import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserById(username);

        return new org.springframework.security.core.userdetails
                .User(user.getUserId(),user.getPassword(),new ArrayList<>());
    }

    public boolean registerNewUser(User newUser) throws Exception {
        User user = userRepository.getUserById(newUser.getUserId());
        if(user != null) {
            throw new UserAlreadyExistsException();
        }
        userRepository.addUser(newUser);
        return true;
    }
}