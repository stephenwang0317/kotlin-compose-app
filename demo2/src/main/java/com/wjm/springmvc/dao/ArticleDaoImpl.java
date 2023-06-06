package com.wjm.springmvc.dao;

import com.wjm.springmvc.bean.Article;
import com.wjm.springmvc.bean.ListResponse;
import com.wjm.springmvc.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ArticleDaoImpl implements ArticleDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Article> getAllArticle() {
        List<Article> ret;
        String sql = "select * from Article";
        ret = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Article.class));
        return ret;
    }

    @Override
    public List<Article> getUserArticle(Integer user_id) {
        List<Article> ret;
        String sql = "select * from Article where art_author=?";
        ret = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Article.class), user_id);
        return ret;
    }

    @Override
    public boolean articleLikePlus(Integer art_id) {
        String sql = "update Article set art_like=art_like+1 where art_id = ?";
        int ret = jdbcTemplate.update(sql, art_id);
        return ret == 1;
    }

    @Override
    public boolean articleLikeMinus(Integer art_id) {
        String sql = "update Article set art_like=art_like-1 where art_id = ?";
        int ret = jdbcTemplate.update(sql, art_id);
        return ret == 1;
    }

    @Override
    public Article getArticleById(Integer art_id) {
        String sql = "select * from Article where art_id=?";
        try {
            Article article = jdbcTemplate.queryForObject(sql,
                    new BeanPropertyRowMapper<>(Article.class), art_id);
            return article;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean addArticle(Article a) {
        if (a.getArt_content().length() > 50) {
            a.setArt_summary(a.getArt_content().substring(0, 44) + " ...");
        } else {
            a.setArt_summary(a.getArt_content());
        }

        Timestamp timestamp = new Timestamp(new Date().getTime());
        String sql = "insert into Article value(null,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int row = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, a.getArt_author());
                ps.setString(2, a.getArt_title());
                ps.setString(3, a.getArt_content());
                ps.setTimestamp(4, timestamp);
                ps.setString(5, a.getArt_summary());
                ps.setInt(6, a.getArt_like());
                ps.setString(7, a.getArt_author_name());
                return ps;
            }
        }, keyHolder);

        a.setArt_time(timestamp.toString());

        int newid = keyHolder.getKey().intValue();
        a.setArt_id(newid);
        return row == 1;
    }
}
