package com.wjm.springmvc.service;

import com.wjm.springmvc.bean.Article;
import com.wjm.springmvc.bean.ListResponse;
import com.wjm.springmvc.bean.User;

import java.util.List;

public interface ArticleService {
    ListResponse<Article> getAllArticles();

    ListResponse<Article> getPageArticles(Integer page);

    ListResponse<Article> getListArticles(List<Integer> list);

    ListResponse<Article> getUserArticles(Integer user_id);

    boolean addArticle(Article a);

    Article getArticleById(Integer art_id);

    boolean articleLikePlus(Integer art_id);

    boolean articleLikeMinus(Integer art_id);

    boolean deleteArticle(Integer art_id);




//    boolean isAuthorOfArticle(Integer art_id, Integer user_id);
}
