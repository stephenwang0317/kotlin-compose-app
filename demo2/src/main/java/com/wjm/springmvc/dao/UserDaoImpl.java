package com.wjm.springmvc.dao;

import com.wjm.springmvc.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public User searchUserById(Integer id) {
//        System.out.println(id);
        String sql = "select * from User where user_id=?";
        try {
//            System.out.println("in try");
            User user = jdbcTemplate.queryForObject(sql,
                    new BeanPropertyRowMapper<>(User.class), id);
            System.out.println(user);
            return user;
        } catch (Exception e) {
//            System.out.println("in catch");
            return null;
        }
    }

    @Override
    public User createUser(User user) {
        String sql = "insert into User value(null,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getUser_name());
                ps.setString(2, user.getUser_pwd());
                ps.setString(3, user.getUser_avater());
                return ps;
            }
        }, keyHolder);

        int newUserId = keyHolder.getKey().intValue();
        user.setUser_id(newUserId);
        return user;
    }

    @Override
    public List<User> listAllUsers() {
        String sql = "select * from User";
        List<User> users = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public User loginUser(User user) {
        String sql = "select * from User where user_id=? and user_pwd=?";
        try {
            User ret = jdbcTemplate.queryForObject(sql,
                    new BeanPropertyRowMapper<>(User.class),
                    user.getUser_id(),
                    user.getUser_pwd());
            return ret;
        } catch (Exception e) {
            return null;
        }

    }
}
