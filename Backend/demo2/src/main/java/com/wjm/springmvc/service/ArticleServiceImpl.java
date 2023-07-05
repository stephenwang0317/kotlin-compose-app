package com.wjm.springmvc.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjm.springmvc.bean.Article;
import com.wjm.springmvc.bean.ListResponse;
import com.wjm.springmvc.bean.User;
import com.wjm.springmvc.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Value("${page-size}")
    Integer PAGE_SIZE;

    @Override
    public ListResponse<Article> getAllArticles() {
        List<Article> articles = articleMapper.getAllArticle();
        ListResponse<Article> ret = new ListResponse<>();
        if (articles.size() > 0) {
            ret.setMsg("success");
        } else {
            ret.setMsg("empty");
        }
        ret.setCode(0);
        ret.setLen(articles.size());
        ret.setList(articles);
        ret.setType(Article.class.getTypeName());
        return ret;
    }

    @Override
    public ListResponse<Article> getPageArticles(Integer page) {
        PageHelper.startPage(page, PAGE_SIZE);
        List<Article> articles = articleMapper.getAllArticle();
//        PageInfo<Article> pageInfo = new PageInfo<>(articles, 1);
        ListResponse<Article> ret = new ListResponse<>();
        if (articles.size() > 0) {
            ret.setMsg("success");
        } else {
            ret.setMsg("empty");
        }
        ret.setCode(0);
        ret.setLen(articles.size());
        ret.setList(articles);
        ret.setType(Article.class.getTypeName());
        return ret;
    }

    @Override
    public ListResponse<Article> getUserArticles(Integer user_id) {
        List<Article> articles = articleMapper.getUserArticle(user_id);
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
        return articleMapper.getArticleById(art_id);
    }

    @Override
    public boolean articleLikePlus(Integer art_id) {
        return articleMapper.articleLikePlus(art_id) != 0;
    }

    @Override
    public boolean articleLikeMinus(Integer art_id) {
        return articleMapper.articleLikeMinus(art_id) != 0;
    }

    @Override
    public boolean addArticle(Article a) {
        if (a.getArt_content().length() > 50) {
            a.setArt_summary(a.getArt_content().substring(0, 44) + " ...");
        } else {
            a.setArt_summary(a.getArt_content());
        }
        Timestamp timestamp = new Timestamp(new Date().getTime());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNow = df.format(timestamp);

        a.setArt_time(dateNow);
        return articleMapper.addArticle(a) != 0;
    }

    @Override
    public ListResponse<Article> getListArticles(List<Integer> list) {
        List<Article> articles = new ArrayList<>();
        for (Integer id : list) {
            Article t = articleMapper.getArticleById(id);
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

    @Override
    public boolean deleteArticle(Integer art_id) {
        return articleMapper.deleteArticle(art_id) == 1;
    }
}
