package com.wjm.springmvc.service;

import com.wjm.springmvc.bean.Article;
import com.wjm.springmvc.bean.ListResponse;
import com.wjm.springmvc.bean.User;
import com.wjm.springmvc.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleDao articleDao;

    @Override
    public ListResponse<Article> getAllArticles() {
        List<Article> articles = articleDao.getAllArticle();
        ListResponse<Article> ret = new ListResponse<>();
        if (articles.size() > 0) {
            ret.setCode(0);
            ret.setMsg("success");
        } else { ret.setMsg("empty"); return ret;}
        ret.setLen(articles.size());
        ret.setList(articles);
        ret.setType(Article.class.getTypeName());
        return ret;
    }

    @Override
    public ListResponse<Article> getUserArticles(Integer user_id) {
        List<Article> articles = articleDao.getUserArticle(user_id);
        ListResponse<Article> ret = new ListResponse<>();
        ret.setLen(articles.size());
        ret.setList(articles);
        ret.setType(Article.class.getTypeName());
        ret.setCode(0);
        ret.setMsg("success");
        return ret;
    }

    @Override
    public Article getArticleById(Integer art_id) {
        return articleDao.getArticleById(art_id);
    }

    @Override
    public boolean articleLikePlus(Integer art_id) {
        return articleDao.articleLikePlus(art_id);
    }

    @Override
    public boolean articleLikeMinus(Integer art_id) {
        return articleDao.articleLikeMinus(art_id);
    }

    @Override
    public boolean addArticle(Article a) {
        return articleDao.addArticle(a);
    }

    @Override
    public ListResponse<Article> getListArticles(List<Integer> list) {
        List<Article> articles = new ArrayList<>();
        for (Integer id : list) {
            Article t = getArticleById(id);
            articles.add(t);
        }
        ListResponse<Article> ret = new ListResponse<>();
        ret.setCode(0);
        ret.setMsg("success");
        ret.setType(Article.class.getTypeName());
        ret.setLen(articles.size());
        ret.setList(articles);
        return ret;
    }
}
