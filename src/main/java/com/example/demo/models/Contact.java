package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.util.*;

@Data

public class Contact {

    private String contactId;
    private String name;
    private String email;
    private String phoneNo;
    private String address;
    private int score;
    private Date dob;

    public Contact(){
    }

    public Contact(String contactId ,String name , String email , String phoneNo ,String address , int score , Date dob){
        this.contactId = contactId ;
        this.name =name ;
        this.email = email ;
        this.phoneNo =phoneNo ;
        this.address =address ;
        this.score= score ;
        this.dob =dob ;
    }

    public void increaseScore() {
        this.score++;
    }
}
