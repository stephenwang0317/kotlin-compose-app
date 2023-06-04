package com.wjm.springmvc.service;

import com.wjm.springmvc.bean.Article;
import com.wjm.springmvc.bean.ListResponse;
import com.wjm.springmvc.bean.User;

import java.util.List;

public interface ArticleService {
    ListResponse<Article> getAllArticles();
    ListResponse<Article> getUserArticles(Integer user_id);

    boolean addArticle(Article a);
}
