package com.example.demo.queries;

import com.example.demo.models.Contact;

public class Queries {

    public static final String CONTACTS_TABLE = "contacts";
    public static final String USER_TABLE = "users";



    public static String getGetAllContactsQuery(String userId) {

        return String.format(
                "select * from %s where userId = \"%s\" ",
                 CONTACTS_TABLE, userId);
    }

    public static String getGetAllContactsSortedByNameQuery(String userId) {
        String query = Queries.getGetAllContactsQuery(userId) + " order by name;";
        System.out.println(query);
        return query;
    }

    public static String getGetAllContactsSortedByScoreQuery(String userId) {
        return Queries.getGetAllContactsQuery(userId) + "order by score desc, name;";
    }



    public static String getGetContactsByNameQuery(String userId, String name) {

        String query = getGetAllContactsQuery(userId) +
                String.format(
                        "and name like \"%s\" " +
                                "order by score desc, name;",
                                name + "%");
        System.out.println(query);
        return query;
    }

    public static String getGetContactByIdQuery(String userId, String contactId) {

        String query =
                String.format(
                        "select * from %s " +
                                " where contactId = \"%s\" and userId = \"%s\" ;",
                        CONTACTS_TABLE, contactId, userId);

        return query;
    }

    public static String getDeleteContactByIdQuery(String contactId) {
        return String.format(
                "delete from %s where contactId = \"%s\";",
                CONTACTS_TABLE,
                contactId);
    }

    public static String getUpdateContactQuery(Contact contact) {


        return String.format(
                "update %s set %s = \"%s\", %s = \"%s\"," +
                        "%s = \"%s\", %s = \"%s\", %s = \"%s\"" +
                        "where contactId = %d;",
                CONTACTS_TABLE,
                "name" , contact.getName() ,
                "email" , contact.getEmail() ,
                "phoneNo" , contact.getPhoneNo() ,
                "address" , contact.getAddress() ,
                "dob" ,contact.getDob() ,
                contact.getContactId()
                );
    }

    public static String getUpsertContactQuery(Contact contact) {

        return String.format(
                "replace into %s(userId,contactId,name,email,phoneNo,address,dob,score) " +
                        "values(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",%d);",
                CONTACTS_TABLE,
                contact.getUserId(),contact.getContactId(), contact.getName(), contact.getEmail(),
                contact.getPhoneNo(), contact.getAddress(), contact.getDob(), contact.getScore());
    }

    public static String getIncrementContactScoreQuery(String contactId) {
        return String.format(
                "update %s " +
                        "set score = score + 1 " +
                        "where contactId = %s;",
                CONTACTS_TABLE, contactId);
    }




}

