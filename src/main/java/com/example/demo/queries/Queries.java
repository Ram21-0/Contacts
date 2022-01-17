package com.example.demo.queries;

import java.util.Locale;

public class Queries {

    public static final String TABLE_NAME = "contacts";

    public static final String INSERT_CONTACT_QUERY =
            String.format("insert into %s(name,email) values(?,?);",TABLE_NAME);

    public static final String GET_ALL_CONTACTS_QUERY =
            String.format("select * from %s;",TABLE_NAME);

    public static final String GET_CONTACTS_BY_NAME =
            String.format("select * from %s where name like \"%s\"",TABLE_NAME,"%s");

    public static final String GET_CONTACTS_BY_FIELD =
            String.format("select * from %s where %s like \"%s\"",TABLE_NAME,"%s","%s");
}
