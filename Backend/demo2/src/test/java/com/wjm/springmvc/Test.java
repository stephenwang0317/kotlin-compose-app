package com.wjm.springmvc;

import com.wjm.springmvc.bean.Article;
import com.wjm.springmvc.bean.Comment;
import com.wjm.springmvc.bean.User;
import com.wjm.springmvc.mapper.ArticleMapper;
import com.wjm.springmvc.mapper.CommentMapper;
import com.wjm.springmvc.mapper.LikeMapper;
import com.wjm.springmvc.mapper.UserMapper;
import com.wjm.springmvc.utils.SqlSessionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Test {

    @org.junit.Test
    public void test() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();

        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        Comment c =
                new Comment(null, 40, 100000, "admin", "给金老师点赞", (new Timestamp(new Date().getTime()).toString()));
        Integer i = commentMapper.createComment(c);
        System.out.println(c);
    }

}
