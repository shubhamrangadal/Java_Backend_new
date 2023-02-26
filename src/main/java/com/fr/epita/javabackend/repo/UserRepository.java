package com.fr.epita.javabackend.repo;


import com.fr.epita.javabackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserRepository {

    @Autowired
    @Qualifier("movieJdbcTemplate")
    private JdbcTemplate userJdbcTemplate;



    public boolean checkIfUserExists(String username){
        return userJdbcTemplate.queryForObject("select count(*) from movieuser where username = ?", new Object[]{username}, Integer.class) > 0;
    }


    public User getUserDetails(String userName,String password){
        return userJdbcTemplate.queryForObject("select * from movieuser where username = ? and userpassword = ?",new Object[]{userName,password}, (rs, rowNum) ->
                new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("userpassword"),
                        rs.getInt("contact_id"),
                        rs.getString("userrole")
                )
        );
    }


    public boolean checkUser(String userName, String password){
        return userJdbcTemplate.queryForObject("select count(*) from movieuser where username = ? and password = ?", new Object[]{userName, password}, Integer.class) > 0;
    }


}
