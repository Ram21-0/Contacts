package com.example.demo.controllers;

import com.example.demo.models.Contact;
import com.example.demo.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactsController {

    @Autowired
    ContactRepository repository;

    @GetMapping("/")
    public List<Contact> getAllContacts() {
        return repository.getAllContacts();
    }

    @GetMapping("/{name}")
    public List<Contact> getContact(@PathVariable String name) {
        return repository.getContactsByName(name);
    }
//
    @PostMapping("/add")
    public Contact addContact(@RequestBody Contact contact) {
        return repository.addContact(contact);
//        return contact;
    }
//
////    @PutMapping("/update/{name}")
//
//    @PatchMapping("/increaseScore")
//    public void updateContactScore(@RequestBody Contact contact) {
//        service.incrementContactScore(contact);
//    }
}
