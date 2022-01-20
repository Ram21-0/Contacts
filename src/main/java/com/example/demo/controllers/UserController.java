package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.AuthRequest;
import com.example.demo.security.AuthResponse;
import com.example.demo.security.JWTUtil;
import com.example.demo.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private static final String path = "/users";

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository repository;

    @CrossOrigin()
    @PostMapping("/register")
    public User addUser(@RequestBody User user) {
        return repository.addUser(user);
    }

    @CrossOrigin()
    @PutMapping("/updateUserDetails")
    public User updateUser(@RequestBody User user) {
        return repository.updateUser(user);
    }

    @CrossOrigin()
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) throws Exception {

        System.out.println("route");

        try {
            System.out.println("try1");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserId(), request.getPassword())
            );
            System.out.println("try");
        }
        catch (BadCredentialsException exception) {
            System.out.println("catch");
            throw new Exception("Invalid credentials",exception);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }


        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserId());
        System.out.println(userDetails);
        final String jwt = jwtUtil.generateToken(userDetails);
        System.out.println("jwt" + jwt);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
