package com.wjm.springmvc.service;

import com.wjm.springmvc.mapper.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeMapper likeMapper;

    @Override
    public boolean userLikeArticle(Integer user_id, Integer art_id) {
        return likeMapper.like(user_id, art_id) == 1;
    }

    @Override
    public boolean userDisLikeArticle(Integer user_id, Integer art_id) {
        return likeMapper.dislike(user_id, art_id) == 1;
    }

    @Override
    public boolean checkIfLike(Integer user_id, Integer art_id) {
        return likeMapper.checkIfLike(user_id, art_id) == 1;
    }

    @Override
    public List<Integer> getUserLike(Integer user_id) {
        return likeMapper.getUserLike(user_id);
    }
}
