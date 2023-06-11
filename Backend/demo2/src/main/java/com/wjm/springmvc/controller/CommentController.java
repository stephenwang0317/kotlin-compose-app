package com.wjm.springmvc.controller;

import com.wjm.springmvc.bean.Comment;
import com.wjm.springmvc.bean.ListResponse;
import com.wjm.springmvc.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/user/{user_id}")
    @ResponseBody
    ListResponse<Comment> getCommentOfUser(@PathVariable Integer user_id) {
        return commentService.getCommentOfUser(user_id);
    }

    @GetMapping("/article/{art_id}")
    @ResponseBody
    ListResponse<Comment> getCommentOfArticle(@PathVariable Integer art_id) {
        return commentService.getCommentOfArticle(art_id);
    }

    @PostMapping("")
    @ResponseBody
    Comment createComment(@RequestBody Comment c){
        boolean f = commentService.createComment(c);
        if(f) return c;
        else return new Comment();
    }
}
