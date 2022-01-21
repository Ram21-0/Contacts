package com.example.demo.security;

import com.example.demo.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest implements Serializable {
    private String userId;
    private String password;

    public static AuthRequest valueOf(User user) {
        return new AuthRequest(user.getUserId(), user.getPassword());
    }
}
