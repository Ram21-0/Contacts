package com.example.demo.security;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserById(username);

//        return new MyUser(user.getUserId(),user.getPassword(),new ArrayList<>(),user);
        return new org.springframework.security.core.userdetails
                .User(user.getUserId(),user.getPassword(),new ArrayList<>());
    }

    public boolean registerNewUser(User newUser) throws Exception {
        User user = userRepository.getUserById(newUser.getUserId());
        if(user != null) {
            throw new Exception("User already exists");
        }
//        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.addUser(newUser);
        System.out.println("User " + newUser);
        return true;
    }
}