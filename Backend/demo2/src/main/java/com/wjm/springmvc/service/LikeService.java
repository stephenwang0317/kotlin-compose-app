package com.wjm.springmvc.service;

import java.util.List;

public interface LikeService {
    boolean userLikeArticle(Integer user_id, Integer art_id);

    boolean userDisLikeArticle(Integer user_id, Integer art_id);

    boolean checkIfLike(Integer user_id, Integer art_id);

    List<Integer> getUserLike(Integer user_id);
}
