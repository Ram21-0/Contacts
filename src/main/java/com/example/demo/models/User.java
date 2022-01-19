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
}
