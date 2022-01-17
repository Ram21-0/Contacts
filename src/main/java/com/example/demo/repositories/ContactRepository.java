package com.example.demo.repositories;

import com.example.demo.models.Contact;
import com.example.demo.queries.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ContactRepository implements ContactRepositoryInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Contact addContact(Contact contact) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement statement =
                    con.prepareStatement(Queries.INSERT_CONTACT_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,contact.getName());
            statement.setString(2, contact.getEmail());
            return statement;
        }, holder);

        return getContactById(holder.getKey().intValue());
    }

    @Override
    public void deleteContact(int id) {

    }

    @Override
    public void editContact(int id) {

    }

    @Override
    public List<Contact> getContactsByName(String name) {
//        System.out.println(String.format(Queries.GET_CONTACTS_BY_NAME,name));
        return jdbcTemplate.query(
                String.format(Queries.GET_CONTACTS_BY_NAME,name + "%"),
                (result,rowNo) -> new Contact(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("email"),
                        result.getInt("score")
                )
        );
    }

    @Override
    public Contact getContactById(int id) {
        return jdbcTemplate.query(
                String.format(Queries.GET_CONTACTS_BY_FIELD,"ID",id + "%"),
                (result,rowNo) -> new Contact(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("email"),
                        result.getInt("score")
                )
        ).stream().findFirst().orElse(null);
    }

    @Override
    public List<Contact> getAllContacts() {
        return jdbcTemplate.query(Queries.GET_ALL_CONTACTS_QUERY,
                (result,rowNo) -> new Contact(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("email"),
                    result.getInt("score")
            )
        );
    }
}
