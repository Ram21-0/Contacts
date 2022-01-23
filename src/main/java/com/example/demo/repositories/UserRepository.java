package com.example.demo.repositories;

import com.example.demo.models.Contact;
import com.example.demo.models.User;
import com.example.demo.queries.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Random;
import com.example.demo.queries.Queries;

@Repository
public class UserRepository implements UserRepositoryInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        System.out.println(Queries.getUpsertUserQuery(user));
        jdbcTemplate.update(Queries.getUpsertUserQuery(user));
        return user;
    }

    @Override
    public User updateUser(User user) {
        System.out.println(Queries.getUpdateUserQuery(user));
        jdbcTemplate.update(Queries.getUpdateUserQuery(user));
        return user;
    }

    @Override
    public User getUserById(String userId) {
        List<User> users = jdbcTemplate.query(Queries.getGetUserByIdQuery(userId),
                new BeanPropertyRowMapper<>(User.class));
        System.out.println(users);
        if(users.size() == 0) return null;
        return users.get(0);
    }
}
