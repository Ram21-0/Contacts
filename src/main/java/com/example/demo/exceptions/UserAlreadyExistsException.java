package com.example.demo.exceptions;

public class UserAlreadyExistsException extends Exception {

    @Override
    public String getLocalizedMessage() {
        return "A user with this email id already exists!";
    }
}
