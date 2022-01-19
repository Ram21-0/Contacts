package com.example.demo.repositories;

import com.example.demo.models.Contact;

import java.util.List;

public interface ContactRepositoryInterface {

    public Contact addContact(Contact contact);

    public void deleteContact(String id);

    public Contact updateContact(Contact contact);

    public List<Contact> getContactsByName(String name);

    public List<Contact> getAllContacts();

    public Contact getContactById(String id);
}
