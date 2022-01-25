package com.example.demo.repositories;

import com.example.demo.models.User;
import com.example.demo.queries.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User addUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        System.out.println(Queries.getUpsertUserQuery(user));
        jdbcTemplate.update(Queries.getUpsertUserQuery(user));
        return user;
    }

    public User updateUser(User user) {
        System.out.println(Queries.getUpdateUserQuery(user));
        jdbcTemplate.update(Queries.getUpdateUserQuery(user));
        return user;
    }

    public User getUserById(String userId) {
        List<User> users = jdbcTemplate.query(Queries.getGetUserByIdQuery(userId),
                new BeanPropertyRowMapper<>(User.class));
        System.out.println(users);
        if(users.size() == 0) return null;
        return users.get(0);
    }
}
