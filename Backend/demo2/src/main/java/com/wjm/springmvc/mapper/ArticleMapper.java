package com.wjm.springmvc.mapper;

import com.wjm.springmvc.bean.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {
    List<Article> getAllArticle();

    List<Article> getUserArticle(@Param("user_id") Integer user_id);

    Article getArticleById(@Param("art_id") Integer art_id);

    Integer addArticle(Article a);

    Integer articleLikePlus(@Param("art_id") Integer art_id);

    Integer articleLikeMinus(@Param("art_id") Integer art_id);

    Integer deleteArticle(@Param("art_id") Integer art_id);
}
