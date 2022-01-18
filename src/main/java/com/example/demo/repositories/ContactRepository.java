package com.example.demo.repositories;

import com.example.demo.models.Contact;
import com.example.demo.queries.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

@Repository
public class ContactRepository implements ContactRepositoryInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // TODO: 18/01/22 RESPONSE AND EXCEPTION HANDLING


    // TODO: 18/01/22 hacky way to maintain userId , need to change this in future
    private static final String userId = "1";

    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

    // generate ID
    private String generateID() {
        long contactId = System.currentTimeMillis();
//        contactId *= getRandomNumber(1, (int) 1e9);
        return Long.toString(contactId);
    }

    @Override
    public Contact addContact(Contact contact) {
        String contactId = generateID();
        contact.setContactId(contactId);
        
        jdbcTemplate.update(Queries.getUpsertContactQuery(contact));
        jdbcTemplate.update(Queries.getInsertIntoRelationQuery(userId,contactId));
        
        return contact;
    }

    @Override
    public void deleteContact(String contactId) {
        jdbcTemplate.update(Queries.getDeleteContactByIdQuery(contactId));
    }

    @Override
    public Contact updateContact(Contact contact) {
        jdbcTemplate.update(Queries.getUpsertContactQuery(contact));
        return contact;
    }

    @Override
    public List<Contact> getContactsByName(String name) {

        return jdbcTemplate.query(Queries.getGetContactsByNameQuery(userId, name),
                new BeanPropertyRowMapper<>(Contact.class)
        );
    }

    @Override
    public Contact getContactById(String contactId) {

        jdbcTemplate.update(Queries.getIncrementContactScoreQuery(contactId));

        List<Contact> contactList = jdbcTemplate.query(Queries.getGetContactByIdQuery(userId,contactId),
                new BeanPropertyRowMapper<>(Contact.class));

        return contactList.get(0);

//        Contact contact = jdbcTemplate.queryForObject(Queries.getGetContactByIdQuery(userId, contactId),
//                new Object[] { contactId },
//                new BeanPropertyRowMapper<>(Contact.class));
//        contact.increaseScore();

//        updateContact(contact);
//        return contact;
    }

    @Override
    public List<Contact> getAllContacts() {
        return jdbcTemplate.query(Queries.getGetAllContactsSortedByNameQuery(userId),
                new BeanPropertyRowMapper<>(Contact.class)
        );
    }
}
