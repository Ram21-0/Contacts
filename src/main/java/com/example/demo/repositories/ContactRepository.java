package com.example.demo.repositories;

import com.example.demo.models.Contact;
import com.example.demo.queries.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Random;

@Repository
public class ContactRepository implements ContactRepositoryInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // TODO: 18/01/22 RESPONSE AND EXCEPTION HANDLING

    // TODO: 18/01/22 hacky way to maintain userId , need to change this in future
    private static final String userId = "ram@flock.com";


    @Override
    public Contact addContact(Contact contact) {
        
        //jdbcTemplate.update(Queries.getUpsertContactQuery(contact));
        System.out.println(Queries.getUpsertContactQuery(contact));
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement statement =
                    con.prepareStatement(Queries.getUpsertContactQuery(contact), Statement.RETURN_GENERATED_KEYS);
            return statement;
        }, holder);

        System.out.println(holder.getKey().intValue());
        contact.setContactId(holder.getKey().intValue());
        // TODO: find a way to get id
        
        return contact;
    }

    @Override
    public void deleteContact(String contactId) {
        jdbcTemplate.update(Queries.getDeleteContactByIdQuery(contactId));
    }

    @Override
    public Contact updateContact(Contact contact) {
        System.out.println(Queries.getUpdateContactQuery(contact));
        jdbcTemplate.update(Queries.getUpdateContactQuery(contact));

        return contact;
    }

    @Override
    public List<Contact> getContactsByName(String name,String userId) {

        return jdbcTemplate.query(Queries.getGetContactsByNameQuery(userId, name),
                new BeanPropertyRowMapper<>(Contact.class)
        );
    }

    @Override
    public Contact getContactById(String contactId,String userId) {

        jdbcTemplate.update(Queries.getIncrementContactScoreQuery(contactId));
        System.out.println(Queries.getIncrementContactScoreQuery(contactId));
        System.out.println(Queries.getGetContactByIdQuery(userId, contactId));
        List<Contact> contactList = jdbcTemplate.query(Queries.getGetContactByIdQuery(userId,contactId),
                new BeanPropertyRowMapper<>(Contact.class));

        return contactList.get(0);
    }

    @Override
    public List<Contact> getAllContacts(String userId) {
        return jdbcTemplate.query(Queries.getGetAllContactsSortedByNameQuery(userId),
                new BeanPropertyRowMapper<>(Contact.class)
        );
    }
}
