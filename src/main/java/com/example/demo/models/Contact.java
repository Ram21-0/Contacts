package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    private int contactId;
    private String name;
    private String email;
    private String phoneNo;
    private String address;
    private int score;
    private Date dob;

    public void increaseScore() {
        this.score++;
    }
}
