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
                                "values(%d,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",0);",
                        CONTACTS_TABLE,
                        contact.getContactId(), contact.getName(), contact.getEmail(),
                        contact.getPhoneNo(), contact.getAddress(), "2000-12-12");

        return insertIntoContacts;
    }

    public static String getInsertIntoRelationQuery(String userId, String contactId) {
        return String.format(
                "insert into %s(userId,contactId) values(%d,%d);",
                USER_CONTACTS_TABLE, userId, contactId);
    }

    public static String getGetAllContactsQuery(String userId) {

//        select * from user_contacts join contacts on contacts.contactid=user_contacts.contactId and userid=2;
//        String table1 = USER_CONTACTS_TABLE, table2 = CONTACTS_TABLE;

        String query =
                String.format(
                        "select * from %s as t1 inner join %s as t2 " +
                                "on t1.contactId = t2.contactId " +
                                "and t1.userId=%d;",
                    USER_CONTACTS_TABLE, CONTACTS_TABLE, userId);

        return query;
    }



    public static String getGetContactsByName(String userId,String name) {
//        String query = "select * from %s where name like \"%s\";";
//        return String.format(query, CONTACTS_TABLE, name + "%");
//        explain select * from  ( select t2.* from user_contacts as t1 inner join contacts as t2 on t1.contactId = t2.contactId and t1.userId = 2 ) as t where name = "name10";


        String query =
                String.format(
                        "select * from ( " +
                                "select t2.* from %s as t1 inner join %s as t2 " +
                                "on t1.contactId = t2.contactId and t1.userId = ? " +
                                ") as t where name like \"?\";",
                        USER_CONTACTS_TABLE, CONTACTS_TABLE);

//        String table1 = USER_CONTACTS_TABLE, table2 = CONTACTS_TABLE;

//        String query =
//                String.format("select * from %s as t1 join %s as t2 on t1.contactId=t2.contactId and t1.userId=%d;",
//                    table1,table2,userId);

        return query;
    }

//    public static final String INSERT_CONTACT_QUERY =
//            String.format("insert into %s(name,email,phoneNo,address,dob) values(?,?,?,?,?);", CONTACTS_TABLE);
//
////    public static final String GET_ALL_CONTACTS_QUERY =
////            String.format("select * from %s;",TABLE_NAME);
//
//    public static final String GET_CONTACTS_BY_NAME =
//            String.format("select * from %s where name like \"%s\"",TABLE_NAME,"%s");
//
//    public static final String GET_CONTACTS_BY_FIELD =
//            String.format("select * from %s where %s like \"%s\"",TABLE_NAME,"%s","%s");
}

//insert into contacts(name,email,phoneNo,address,dob,score) values("sita","sita@flock.com","12345","address","2000-10-25",0);