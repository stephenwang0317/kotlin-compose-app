package com.wjm.springmvc.service;

import com.wjm.springmvc.bean.Comment;
import com.wjm.springmvc.bean.ListResponse;

import java.util.List;

public interface CommentService {
    ListResponse<Comment> getCommentOfArticle(Integer articleId);

    ListResponse<Comment> getCommentOfUser(Integer userId);

    Boolean createComment(Comment c);
}
