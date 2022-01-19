package com.example.demo.controllers;

import com.example.demo.models.Contact;
import com.example.demo.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactsController {

    private static final String path = "/contacts";

    @Autowired
    ContactRepository repository;

    @CrossOrigin("http://localhost:3000")
    @GetMapping(path)
    public List<Contact> getAllContacts() {
        return repository.getAllContacts();
    }

    @GetMapping(path + "/{name}")
    public List<Contact> getContact(@PathVariable String name) {
        return repository.getContactsByName(name);
    }
//
    @PostMapping(path + "/add")
    public Contact addContact(@RequestBody Contact contact) {
        return repository.addContact(contact);
    }

    @DeleteMapping(path + "/delete/{contactId}")
    public boolean deleteContact(@PathVariable String contactId) {
        repository.deleteContact(contactId);
        return true;
    }

    @PutMapping(path + "/update")
    public Contact updateContact(@RequestBody Contact contact) {
        return repository.updateContact(contact);
    }

    @PatchMapping(path + "/getcontact/{contactId}")
    public Contact updateContactScore(@PathVariable String contactId) {
        return repository.getContactById(contactId);
    }
}
