package com.wjm.springmvc.service;

import com.wjm.springmvc.bean.Comment;
import com.wjm.springmvc.bean.ListResponse;
import com.wjm.springmvc.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public ListResponse<Comment> getCommentOfArticle(Integer articleId) {
        List<Comment> comments = commentMapper.getCommentOfArticle(articleId);
        ListResponse<Comment> ret = new ListResponse<>();
        if (comments.size() > 0) {
            ret.setCode(0);
            ret.setMsg("success");
        } else {
            ret.setMsg("empty");
            return ret;
        }
        ret.setLen(comments.size());
        ret.setList(comments);
        ret.setType(Comment.class.getTypeName());
        return ret;
    }

    @Override
    public ListResponse<Comment> getCommentOfUser(Integer userId) {
        List<Comment> comments = commentMapper.getCommentOfUser(userId);
        ListResponse<Comment> ret = new ListResponse<>();
        if (comments.size() > 0) {
            ret.setCode(0);
            ret.setMsg("success");
        } else {
            ret.setMsg("empty");
            return ret;
        }
        ret.setLen(comments.size());
        ret.setList(comments);
        ret.setType(Comment.class.getTypeName());
        return ret;
    }

    @Override
    public Boolean createComment(Comment c) {
        return commentMapper.createComment(c) == 1;
    }
}
