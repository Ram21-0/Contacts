package com.example.demo.controllers;

import com.example.demo.models.Contact;
import com.example.demo.repositories.ContactRepository;
import com.example.demo.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin()
@RestController
public class ContactsController {

    private static final String path = "/contacts";

    @Autowired
    ContactRepository repository;

    @CrossOrigin()
    @GetMapping(path)
    public List<Contact> getAllContacts(Principal principal) {
//        System.out.println(principal.getName());
        String userId = principal.getName();
        return repository.getAllContacts(userId);
    }

    @CrossOrigin()
    @GetMapping(path + "/{name}")
    public List<Contact> getContact(@PathVariable String name, Principal principal) {
        String userId = principal.getName();
        return repository.getContactsByName(name,userId);
    }

    @CrossOrigin()
    @PostMapping(path + "/add")
    public Contact addContact(@RequestBody Contact contact) {
        return repository.addContact(contact);
    }

    @CrossOrigin()
    @DeleteMapping(path + "/delete/{contactId}")
    public boolean deleteContact(@PathVariable String contactId) {
        repository.deleteContact(contactId);
        return true;
    }

    @CrossOrigin()
    @PutMapping(path + "/update")
    public Contact updateContact(@RequestBody Contact contact) {
        return repository.updateContact(contact);
    }

    @CrossOrigin()
    @PostMapping(path + "/getcontact/{contactId}")
    public Contact updateContactScore(@PathVariable String contactId, Principal principal) {
        String userId = principal.getName();
        return repository.getContactById(contactId,userId);
    }
}
