package com.example.demo.models;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String userId;
    private String password;
    private String name;
    private String phoneNo;
    private String address;
    private Date dob;
    private Timestamp created_at;
    private Timestamp modified_at;

    public User(User user) {
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.dob = user.getDob();
        this.phoneNo = user.getPhoneNo();
        this.address = user.getAddress();
    }
}
