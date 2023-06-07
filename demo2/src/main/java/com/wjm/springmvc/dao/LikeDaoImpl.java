package com.wjm.springmvc.dao;

import com.wjm.springmvc.bean.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    @Override
    public boolean checkIfLike(Integer user_id, Integer art_id) {
        String sql = "select like_id from Likes where user_id=? and art_id=?";
        Integer like_id = 0;
        try {
            like_id = jdbcTemplate.queryForObject(sql,
                    Integer.class, user_id, art_id);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }
}
