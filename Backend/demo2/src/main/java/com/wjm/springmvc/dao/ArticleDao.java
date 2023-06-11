package com.wjm.springmvc.dao;

import com.wjm.springmvc.bean.Article;
import com.wjm.springmvc.bean.User;

import java.util.List;

public interface ArticleDao {
    List<Article> getAllArticle();
    List<Article> getUserArticle(Integer user_id);
    Article getArticleById(Integer art_id);

    boolean addArticle(Article a);
    boolean articleLikePlus(Integer art_id);
    boolean articleLikeMinus(Integer art_id);
}
