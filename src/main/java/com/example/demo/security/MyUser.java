package com.example.demo.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

//@Getter
public class MyUser extends User {

    private static final long serialVersionUID = -3531439484732724601L;

    private final com.example.demo.models.User userObject;

    public MyUser(String username, String password,
                  Collection<org.springframework.security.core.GrantedAuthority> authorities,
                  com.example.demo.models.User userObject) {

        super(username, password, authorities);
        this.userObject = userObject;
    }

    public com.example.demo.models.User getUserObject() {
        return userObject;
    }

//    public static long getSerialversionuid() {
//        return serialVersionUID;
//    }
}
