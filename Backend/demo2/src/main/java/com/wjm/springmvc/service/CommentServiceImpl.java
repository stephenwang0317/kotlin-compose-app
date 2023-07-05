package com.wjm.springmvc.service;

import com.wjm.springmvc.bean.Comment;
import com.wjm.springmvc.bean.ListResponse;
import com.wjm.springmvc.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        Timestamp timestamp = new Timestamp(new Date().getTime());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNow= df.format(timestamp);
        c.setCmt_time(dateNow);

        return commentMapper.createComment(c) == 1;
    }
}
