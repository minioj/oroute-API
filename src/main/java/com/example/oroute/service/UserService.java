package com.example.oroute.service;

import com.example.oroute.jpa.DaoSupport;
import com.example.oroute.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService extends DaoSupport<User> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String insertUser(User user) {
        save(user);
        Long uid = user.getId() + 20200202;
        return String.valueOf(uid);
    }
}
