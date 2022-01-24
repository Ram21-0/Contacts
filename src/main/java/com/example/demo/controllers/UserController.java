package com.example.demo.controllers;

import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.AuthRequest;
import com.example.demo.security.AuthResponse;
import com.example.demo.security.JWTUtil;
import com.example.demo.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @CrossOrigin()
    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User user) {
//        repository.addUser(user);
        System.out.println("user at controller " + user);
        User newUser = new User(user);
        try {
            userDetailsService.registerNewUser(newUser);
            return authenticate(AuthRequest.valueOf(user));
        }
        catch (UserAlreadyExistsException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred");
        }
    }

    @CrossOrigin()
    @PutMapping("/updateUserDetails")
    public User updateUser(@RequestBody User user) {
        return repository.updateUser(user);
//        return authenticate(AuthRequest.valueOf(user));
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
            exception.printStackTrace();
            System.out.println(ResponseEntity.badRequest().body("Invalid credentials"));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//            throw exception;
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred");
//            throw e;
        }


        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserId());
        System.out.println(userDetails);

        final String jwt = jwtUtil.generateToken(userDetails);
        System.out.println("jwt" + jwt);
        return ResponseEntity.ok(new AuthResponse(jwt, repository.getUserById(request.getUserId())));
    }
}
