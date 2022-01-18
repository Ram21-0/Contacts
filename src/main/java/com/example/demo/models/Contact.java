package com.example.demo.models;

import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {

    private String contactId;
    private String name;
    private String email;
    private String phoneNo;
    private String address;
    private int score;
    private Date dob;
}
