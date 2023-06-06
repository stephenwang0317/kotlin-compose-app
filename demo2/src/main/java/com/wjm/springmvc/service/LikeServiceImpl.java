package com.wjm.springmvc.service;

import com.wjm.springmvc.dao.LikeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeDao likeDao;

    @Override
    public boolean userLikeArticle(Integer user_id, Integer art_id) {
        return likeDao.like(user_id, art_id);
    }

    @Override
    public boolean userDisLikeArticle(Integer user_id, Integer art_id) {
        return likeDao.dislike(user_id, art_id);
    }
}
