package com.example.demo.models;

import java.util.*;

public class Contact {

    int id;
    private String name;
    private String email;
    private int score;

    public Contact(int id, String name, String email, int score) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.score = score;
    }

    public Contact() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore() {
        this.score++;
    }

    @Override
    public String toString() {

        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", score=" + score +
                '}';
    }
}
