package com.example.demo.queries;

import java.util.Locale;
import java.util.Map;

public class Queries {

    public static final String CONTACTS_TABLE = "contacts";
    public static final String USER_TABLE = "users";
    private static final String USER_CONTACTS_TABLE = "user_contacts";

    public static String getInsertContactQuery() {
        String query = "insert into %s(name,email,phone,address,dob) values(?,?,?,?,?);";
        return String.format(query, CONTACTS_TABLE);
    }

    public static String getGetAllContactsQuery(String userId) {
        String query = "select * from %s ;";
        return String.format(query, CONTACTS_TABLE);
    }

    public static String getGetContactsByName(String name) {
        String query = "select * from %s where name like \"%s\";";
        return String.format(query, CONTACTS_TABLE, name + "%");
    }

    public static final String INSERT_CONTACT_QUERY =
            String.format("insert into %s(name,email,phoneNo,address,dob) values(?,?,?,?,?);", CONTACTS_TABLE);

    public static final String GET_ALL_CONTACTS_QUERY =
            String.format("select * from %s;",TABLE_NAME);

    public static final String GET_CONTACTS_BY_NAME =
            String.format("select * from %s where name like \"%s\"",TABLE_NAME,"%s");

    public static final String GET_CONTACTS_BY_FIELD =
            String.format("select * from %s where %s like \"%s\"",TABLE_NAME,"%s","%s");
}

//insert into contacts(name,email,phoneNo,address,dob,score) values("sita","sita@flock.com","12345","address","2000-10-25",0);