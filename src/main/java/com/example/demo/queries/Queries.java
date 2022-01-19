package com.example.demo.queries;

import com.example.demo.models.Contact;

public class Queries {

    public static final String CONTACTS_TABLE = "contacts";
    public static final String USER_TABLE = "users";
    private static final String USER_CONTACTS_TABLE = "user_contacts";

//    public static String getInsertIntoContactsQuery(Contact contact) {
//
//        return String.format(
//                "insert into %s(contactId,name,email,phoneNo,address,dob,score) " +
//                        "values(%s,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",0);",
//                CONTACTS_TABLE,
//                contact.getContactId(), contact.getName(), contact.getEmail(),
//                contact.getPhoneNo(), contact.getAddress(), "2000-12-12");
//    }

    public static String getInsertIntoRelationQuery(String userId, String contactId) {
        return String.format(
                "insert into %s(userId,contactId) values(%s,%s);",
                USER_CONTACTS_TABLE, userId, contactId);
    }

    public static String getGetAllContactsQuery(String userId) {

        return String.format(
                "select * from %s as t1 inner join %s as t2 " +
                        "on t1.contactId = t2.contactId " +
                        "and t1.userId=%s",
                USER_CONTACTS_TABLE, CONTACTS_TABLE, userId);
    }

    public static String getGetAllContactsSortedByNameQuery(String userId) {
        String query = Queries.getGetAllContactsQuery(userId) + " order by name;";
        System.out.println(query);
        return query;
    }

    public static String getGetAllContactsSortedByScoreQuery(String userId) {
        return Queries.getGetAllContactsQuery(userId) + "order by score desc, name;";
    }

//    select t2.* from %s as t1 inner join %s as t2
//    on t1.contactId = t2.contactId and t1.userId = \"%s\" AND NAME = "GG";

    public static String getGetContactsByNameQuery(String userId, String name) {

        String query =
                String.format(
                        "select * from ( " +
                                "select t2.* from %s as t1 inner join %s as t2 " +
                                "on t1.contactId = t2.contactId and t1.userId = \"%s\" " +
                                ") as t where name like \"%s\" " +
                                "order by score desc, name;",
                        USER_CONTACTS_TABLE, CONTACTS_TABLE,
                        userId, name + "%");
//        System.out.println(query);
        return query;
    }

    public static String getGetContactByIdQuery(String userId, String contactId) {

        String query =
                String.format(
                        "select * from ( " +
                                "select t2.* from %s as t1 inner join %s as t2 " +
                                "on t1.contactId = t2.contactId and t1.userId = \"%s\" " +
                                ") as t where contactId = \"%s\";",
                        USER_CONTACTS_TABLE, CONTACTS_TABLE,
                        userId, contactId);

        System.out.println(query);
        return query;
    }

    public static String getDeleteContactByIdQuery(String contactId) {
        return String.format(
                "delete from %s where contactId = \"%s\";",
                CONTACTS_TABLE,
                contactId);
    }

    public static String getUpsertContactQuery(Contact contact) {

        return String.format(
                "replace into %s(contactId,name,email,phoneNo,address,dob,score) " +
                        "values(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",%d);",
                CONTACTS_TABLE,
                contact.getContactId(), contact.getName(), contact.getEmail(),
                contact.getPhoneNo(), contact.getAddress(), "2000-12-12", contact.getScore());
    }

    public static String getIncrementContactScoreQuery(String contactId) {
        return String.format(
                "update %s " +
                        "set score = score + 1 " +
                        "where contactId = %s;",
                CONTACTS_TABLE, contactId);
    }


}

