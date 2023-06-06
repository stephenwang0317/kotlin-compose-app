package com.wjm.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LikeDaoImpl implements LikeDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean like(Integer user_id, Integer art_id) {
        String sql = "insert into Likes value(null,?,?)";
        int rows = jdbcTemplate.update(sql, user_id, art_id);
        return rows == 1;
    }

    @Override
    public boolean dislike(Integer user_id, Integer art_id) {
        String sql = "delete from Likes where user_id=? and art_id=?";
        int rows = jdbcTemplate.update(sql, user_id, art_id);
        return rows == 1;
    }
}
