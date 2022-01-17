package com.example.demo.repositories;

import com.example.demo.models.Contact;
import com.example.demo.queries.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



@Repository
public class ContactRepository implements ContactRepositoryInterface {
    // hacky way to maintain userId , need to change this in future
    private static final String userId ="1"  ;

    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

    // tries to generate a truly random ID
    private String genID(){
        long contactId = (long) (System.currentTimeMillis() % (1e9));
        contactId *= getRandomNumber(1 , (int)1e9)  ;
        return Long.toString(contactId) ;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Contact addContact(Contact contact) {
        String contactId = genID() ;
        contact.setContactId(contactId);

        String query = Queries.getInsertIntoContactsQuery(contact);
        System.out.println("Executing the query .. " + query);

        jdbcTemplate.update(Queries.getInsertIntoContactsQuery(contact));
        jdbcTemplate.update(Queries.getInsertIntoRelationQuery(userId, contactId));

        return contact;

    }

    @Override
    public void deleteContact(String contactId) {
        jdbcTemplate.update(Queries.getDeleteContactById(contactId));
    }

    @Override
    public void editContact(Contact contact) {
        String contactId = contact.getContactId() ;
        jdbcTemplate.update(Queries.getUpdateContact(contact)) ;
    }

    @Override
    public List<Contact> getContactsByName(String name) {
     return jdbcTemplate.query(Queries.getGetContactsByName(userId ,name),
            new BeanPropertyRowMapper<Contact>(Contact.class)
       ) ;
    }

    @Override
    public Contact getContactById(String ContactId) {
        return jdbcTemplate.queryForObject(Queries.getGetContactsById(ContactId) ,
                new Object[]{ContactId} ,
                new BeanPropertyRowMapper<Contact>(Contact.class)) ;
    }

    @Override
    public List<Contact> getAllContacts() {
        return jdbcTemplate.query(Queries.getGetAllContactsQuery(userId),
            new BeanPropertyRowMapper<Contact>(Contact.class)
        ) ;
    }
}
