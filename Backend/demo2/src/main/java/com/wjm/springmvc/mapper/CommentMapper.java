package com.wjm.springmvc.mapper;

import com.wjm.springmvc.bean.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CommentMapper {
    List<Comment> getCommentOfArticle(@Param("art_id") Integer articleId);

    List<Comment> getCommentOfUser(@Param("user_id") Integer userId);

    Integer createComment(Comment c);
}
