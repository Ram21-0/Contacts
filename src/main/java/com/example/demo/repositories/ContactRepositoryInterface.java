package com.example.demo.repositories;

import com.example.demo.models.Contact;

import java.util.List;

public interface ContactRepositoryInterface {

    public Contact addContact(Contact contact);
    public void deleteContact(int id);
    public void editContact(int id);
    public List<Contact> getContactsByName(String name);
    public List<Contact> getAllContacts();
    public Contact getContactById(int id);
}
