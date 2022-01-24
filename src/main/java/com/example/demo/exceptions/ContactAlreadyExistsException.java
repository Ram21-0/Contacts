package com.example.demo.exceptions;

public class ContactAlreadyExistsException extends Exception {

    @Override
    public String getLocalizedMessage() {
        return "A contact with this email id already exists!";
    }
}
