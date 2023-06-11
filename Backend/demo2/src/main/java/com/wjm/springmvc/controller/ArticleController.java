package com.wjm.springmvc.controller;

import com.wjm.springmvc.bean.Article;
import com.wjm.springmvc.bean.BaseResponse;
import com.wjm.springmvc.bean.ListResponse;
import com.wjm.springmvc.service.ArticleService;
import com.wjm.springmvc.service.LikeService;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @Autowired
    LikeService likeService;

    @GetMapping("")
    @ResponseBody
    ListResponse<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/user/{user_id}")
    @ResponseBody
    ListResponse<Article> getUserArticles(@PathVariable Integer user_id) {
        return articleService.getUserArticles(user_id);
    }

    @PostMapping("")
    @ResponseBody
    Article addArticle(@RequestBody Article a) {
        boolean flag = articleService.addArticle(a);
        if (flag) return a;
        else return new Article();
    }

    @GetMapping("/{art_id}")
    @ResponseBody
    Article getArticleById(@PathVariable Integer art_id) {
        Article ret = articleService.getArticleById(art_id);
        return ret == null ? new Article() : ret;
    }

    @PutMapping("/{user_id}/{art_id}")
    @ResponseBody
    BaseResponse userLikeArticle(
            @PathVariable Integer user_id,
            @PathVariable Integer art_id) {
        boolean f1 = articleService.articleLikePlus(art_id);
        boolean f2 = likeService.userLikeArticle(user_id, art_id);
        if (f1 && f2) {
            return new BaseResponse(0, "success");
        } else {
            return new BaseResponse();
        }
    }

    @DeleteMapping("/{user_id}/{art_id}")
    @ResponseBody
    BaseResponse userDislikeArticle(
            @PathVariable Integer user_id,
            @PathVariable Integer art_id
    ) {
        boolean f1 = articleService.articleLikeMinus(art_id);
        boolean f2 = likeService.userDisLikeArticle(user_id, art_id);
        if (f1 && f2) {
            return new BaseResponse(0, "success");
        } else {
            return new BaseResponse();
        }
    }

    @GetMapping("/{user_id}/{art_id}")
    @ResponseBody
    BaseResponse checkIfLike(
            @PathVariable Integer user_id,
            @PathVariable Integer art_id
    ) {
        if (likeService.checkIfLike(user_id, art_id)) {
            return new BaseResponse(2, "yes");
        } else {
            return new BaseResponse(2, "no");
        }
    }

    @GetMapping("/like/{user_id}")
    @ResponseBody
    ListResponse<Article> getUserLike(
            @PathVariable Integer user_id
    ) {
        List<Integer> art_ids = likeService.getUserLike(user_id);
        return articleService.getListArticles(art_ids);
    }
}
