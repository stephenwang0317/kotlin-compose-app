package com.wjm.springmvc.dao;

import com.wjm.springmvc.bean.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommentDao {

    List<Comment> getCommentOfArticle(Integer articleId);

    List<Comment> getCommentOfUser(Integer userId);

    Boolean createComment(Comment c);
}
