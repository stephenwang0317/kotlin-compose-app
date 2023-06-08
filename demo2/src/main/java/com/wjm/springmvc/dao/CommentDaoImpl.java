package com.wjm.springmvc.dao;

import com.wjm.springmvc.bean.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Comment> getCommentOfArticle(Integer articleId) {
        List<Comment> ret;
        String sql = "select * from Comment where cmt_art_id = ?";
        ret = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Comment.class), articleId);
        return ret;
    }

    @Override
    public List<Comment> getCommentOfUser(Integer userId) {
        List<Comment> ret;
        String sql = "select * from Comment where cmt_author = ?";
        ret = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Comment.class), userId);
        return ret;
    }

    @Override
    public Boolean createComment(Comment c) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        String sql = "INSERT INTO Comment VALUE(null,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int row = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, c.getCmt_art_id());
                ps.setInt(2, c.getCmt_author());
                ps.setString(3, c.getCmt_content());
                ps.setTimestamp(4, timestamp);
                ps.setString(5, c.getCmt_author_name());
                return ps;
            }
        }, keyHolder);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNow= df.format(timestamp);
        c.setCmt_time(dateNow);

        c.setCmt_id(keyHolder.getKey().intValue());
        return row == 1;
    }
}
