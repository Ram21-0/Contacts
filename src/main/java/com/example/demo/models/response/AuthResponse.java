package com.example.demo.models.response;

import com.example.demo.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

@Getter
@NoArgsConstructor
@Setter
public class AuthResponse implements Serializable {

    @Getter
    @ToString
    private class UserDataOnResponse implements Serializable {
        private String userId;
        private String name;
        private String phoneNo;
        private String address;
        private Date dob;

        public UserDataOnResponse(User user) {
            this.userId = user.getUserId();
            this.name = user.getName();
            this.address = user.getAddress();
            this.phoneNo = user.getPhoneNo();
            this.dob = user.getDob();
        }
    }

    private String jwt;
    private UserDataOnResponse user;

    public AuthResponse(String jwt,User user) {
        this.jwt = jwt;
        this.user = new UserDataOnResponse(user);
    }
}
