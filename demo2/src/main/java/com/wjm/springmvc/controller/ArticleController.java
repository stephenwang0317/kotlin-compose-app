package com.wjm.springmvc.controller;

import com.wjm.springmvc.bean.Article;
import com.wjm.springmvc.bean.ListResponse;
import com.wjm.springmvc.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @GetMapping("")
    @ResponseBody
    ListResponse<Article> getAllArticles(){
        return articleService.getAllArticles();
    }

    @GetMapping("/{user_id}")
    @ResponseBody
    ListResponse<Article> getUserArticles(@PathVariable Integer user_id){
        return articleService.getUserArticles(user_id);
    }

    @PostMapping("")
    @ResponseBody
    Article addArticle(@RequestBody Article a){
        boolean flag = articleService.addArticle(a);
        if(flag) return a;
        else return new Article();
    }
}
