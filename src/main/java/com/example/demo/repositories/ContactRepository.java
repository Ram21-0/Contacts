package com.example.demo.repositories;

import com.example.demo.exceptions.ContactAlreadyExistsException;
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
import java.util.Optional;

@Repository
public class ContactRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Contact addContact(Contact contact) throws Exception {

        Optional<Contact> contactByEmail = getContactByEmail(contact.getUserId(), contact.getEmail());
        if(contactByEmail.isPresent()) {
            throw new ContactAlreadyExistsException();
        }

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement statement =
                    con.prepareStatement(Queries.getInsertContactQuery(contact), Statement.RETURN_GENERATED_KEYS);
            return statement;
        }, holder);

        System.out.println(holder.getKey().intValue());
        contact.setContactId(holder.getKey().intValue());
        return contact;
    }

    public void deleteContact(String contactId) {
        jdbcTemplate.update(Queries.getDeleteContactByIdQuery(contactId));
    }

    public Contact updateContact(Contact contact) throws ContactAlreadyExistsException {
        Optional<Contact> contactByEmail = getContactByEmail(contact.getUserId(), contact.getEmail());
        if(contactByEmail.isPresent()
                && contactByEmail.get().getContactId() != contact.getContactId()) {
            throw new ContactAlreadyExistsException();
        }

        jdbcTemplate.update(Queries.getUpdateContactQuery(contact));

        return contact;
    }

    public List<Contact> getContactsByName(String name,String userId) {
        return jdbcTemplate.query(Queries.getGetContactsByNameQuery(userId, name),
                new BeanPropertyRowMapper<>(Contact.class)
        );
    }

    public Optional<Contact> getContactByEmail(String userId, String email) {
        List<Contact> list = jdbcTemplate.query(Queries.getGetContactByEmailQuery(userId, email),
                new BeanPropertyRowMapper<>(Contact.class));

        if(list.isEmpty()) return Optional.empty();
        return Optional.of(list.get(0));
    }

    public Contact getContactById(String contactId,String userId) {
        jdbcTemplate.update(Queries.getIncrementContactScoreQuery(contactId));
        List<Contact> contactList = jdbcTemplate.query(Queries.getGetContactByIdQuery(userId,contactId),
                new BeanPropertyRowMapper<>(Contact.class));

        return contactList.get(0);
    }

    public List<Contact> getAllContacts(String userId) {
        return jdbcTemplate.query(Queries.getGetAllContactsSortedByNameQuery(userId),
                new BeanPropertyRowMapper<>(Contact.class)
        );
    }
}
