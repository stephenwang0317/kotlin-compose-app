package com.wjm.springmvc.dao;

import java.util.List;

public interface LikeDao {

    boolean like(Integer user_id, Integer art_id);

    boolean dislike(Integer user_id, Integer art_id);

    boolean checkIfLike(Integer user_id, Integer art_id);

    List<Integer> getUserLike(Integer user_id);
}
