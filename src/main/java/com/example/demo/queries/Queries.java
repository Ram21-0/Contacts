package com.example.demo.queries;

import com.example.demo.models.Contact;

import java.util.Locale;
import java.util.Map;

public class Queries {

    public static final String CONTACTS_TABLE = "contacts";
    public static final String USER_TABLE = "users";
    private static final String USER_CONTACTS_TABLE = "user_contacts";

    public static String getInsertIntoContactsQuery(Contact contact) {
        String insertIntoContacts =
                String.format(
                        "insert into %s(contactId,name,email,phoneNo,address,dob,score) " +
                                "values(%s,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",0);",
                        CONTACTS_TABLE,
                        contact.getContactId(), contact.getName(), contact.getEmail(),
                        contact.getPhoneNo(), contact.getAddress(), "2000-12-12");

        return insertIntoContacts;
    }

    public static String getInsertIntoRelationQuery(String userId, String contactId) {
        return String.format(
                "insert into %s(userId,contactId) values(%s,%s);",
                USER_CONTACTS_TABLE, userId, contactId);
    }

    public static String getGetAllContactsQuery(String userId) {

        String query =
                String.format(
                        "select * from %s as t1 inner join %s as t2 " +
                                "on t1.contactId = t2.contactId " +
                                "and t1.userId=%s;",
                    USER_CONTACTS_TABLE, CONTACTS_TABLE, userId);

        return query;
    }



    public static String getGetContactsByName(String userId,String name) {

        String query =
                String.format(
                        "select * from ( " +
                                "select t2.* from %s as t1 inner join %s as t2 " +
                                "on t1.contactId = t2.contactId and t1.userId = ? " +
                                ") as t where name like \"?\";",
                        USER_CONTACTS_TABLE, CONTACTS_TABLE);
        return query;
    }

    public static String getGetContactsById(String contactId ) {

        String query =
                String.format(
                        "select * from %s where contactId = ? " ,CONTACTS_TABLE, contactId) ;

        return query;
    }

    public static String getDeleteContactById(String contactId){
        String query =
                String.format(
                        "delete from %s where contactId = ? " ,CONTACTS_TABLE , contactId) ;
        return query ;
    }

    public static String getUpdateContact(Contact contact){

        // to be completed
        String query =
                String.format(
                        "UPDATE CONTACTS" +
                                " SET ..."
                ) ;


        return query;
    }



}

