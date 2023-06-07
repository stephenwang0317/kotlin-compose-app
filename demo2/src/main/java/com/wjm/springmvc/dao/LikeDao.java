package com.wjm.springmvc.dao;

public interface LikeDao {

    boolean like(Integer user_id, Integer art_id);

    boolean dislike(Integer user_id, Integer art_id);

    boolean checkIfLike(Integer user_id, Integer art_id);
}
