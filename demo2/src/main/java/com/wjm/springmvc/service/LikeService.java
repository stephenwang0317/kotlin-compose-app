package com.wjm.springmvc.service;

public interface LikeService {
    boolean userLikeArticle(Integer user_id, Integer art_id);

    boolean userDisLikeArticle(Integer user_id, Integer art_id);
}
