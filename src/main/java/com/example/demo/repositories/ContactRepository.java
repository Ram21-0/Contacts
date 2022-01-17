package com.example.demo.repositories;

import com.example.demo.models.Contact;
import com.example.demo.queries.Queries;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final String userId ="1"  ;

    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }
    private String genID(){
        // tries to generate a truly random ID
        long contactId = (long) (System.currentTimeMillis() % (1e9));
        contactId *= getRandomNumber(1 , (int)1e9)  ;
        return Long.toString(contactId) ;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Contact addContact(Contact contact) {
//        KeyHolder holder = new GeneratedKeyHolder();

        String contactId = genID() ;

        contact.setContactId(contactId);

        String qqq = Queries.getInsertIntoContactsQuery(contact);
        System.out.println(qqq);
        jdbcTemplate.update(Queries.getInsertIntoContactsQuery(contact));
        jdbcTemplate.update(Queries.getInsertIntoRelationQuery(userId, contactId));
//        jdbcTemplate.update(con -> {
//            PreparedStatement statement =
//                    con.prepareStatement(Queries.getInsertIntoContactsQuery(), Statement.RETURN_GENERATED_KEYS);
//            statement.setInt(1,id);
//            statement.setString(2, contact.getName());
//            statement.setString(3, contact.getEmail());
//            statement.setString(4, contact.getPhoneNo());
//            statement.setString(5, contact.getAddress());
//            statement.setDate(6, Date.valueOf("2000-10-11"));
//
////            statement.setInt(7, 1);
////            statement.setLong(8, id);
//            return statement;
//        }, holder);

        return contact;

//        System.out.println(holder);

//        return getContactById(holder.getKey());
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
//        return jdbcTemplate.query(
//                String.format(Queries.GET_CONTACTS_BY_NAME,name + "%"),
//                (result,rowNo) -> new Contact(
//                        result.getInt("id"),
//                        result.getString("name"),
//                        result.getString("email"),
//                        result.getInt("score")
//                )
//        );
        return new ArrayList<>();
    }

    @Override
    public Contact getContactById(int id) {
//        return jdbcTemplate.query(
//                String.format(Queries.GET_CONTACTS_BY_FIELD,"ID",id + "%"),
//                (result,rowNo) -> new Contact(
//                        result.getInt("id"),
//                        result.getString("name"),
//                        result.getString("email"),
//                        result.getInt("score")
//                )
//        ).stream().findFirst().orElse(null);
        return new Contact();
    }

    @Override
    public List<Contact> getAllContacts() {
        return jdbcTemplate.query(Queries.getGetAllContactsQuery(userId),
                (result,rowNo) -> new Contact(
                    result.getString("contactId"),
                    result.getString("name"),
                    result.getString("email"),
                        result.getString("phoneNo"),
                    result.getString("address"),
                        result.getInt("score"),
                        new Date(2000,10,10)

                        /// Date conversion between SQL and java
                ));

//        return null;
    }
}
