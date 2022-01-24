package com.example.demo.controllers;

import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.models.request.AuthRequest;
import com.example.demo.models.response.AuthResponse;
import com.example.demo.security.JWTUtil;
import com.example.demo.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

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
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User newUser = new User(user);
        try {
            userDetailsService.registerNewUser(newUser);
            return authenticate(AuthRequest.valueOf(user));
        }
        catch (UserAlreadyExistsException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred");
        }
    }

    @CrossOrigin()
    @PutMapping("/updateUserDetails")
    public User updateUser(@RequestBody User user) {
        return repository.updateUser(user);
    }

    @CrossOrigin()
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserId(), request.getPassword())
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserId());
            final String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(jwt, repository.getUserById(request.getUserId())));
        }
        catch (BadCredentialsException exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred");
        }
    }
}
