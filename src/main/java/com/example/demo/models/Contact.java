package com.example.demo.models;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {
    private String userId ;
    private int contactId;
    private String name;
    private String email;
    private String phoneNo;
    private String address;
    private int score;
    private Date dob;
    private Timestamp created_at  ;
    private Timestamp modified_at;
}
