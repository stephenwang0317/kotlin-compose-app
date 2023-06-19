package com.wjm.springmvc.mapper;

import com.wjm.springmvc.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LikeMapper {
    Integer like(@Param("user_id") Integer user_id, @Param("art_id") Integer art_id);

    Integer dislike(@Param("user_id") Integer user_id, @Param("art_id") Integer art_id);

    Integer checkIfLike(@Param("user_id") Integer user_id, @Param("art_id") Integer art_id);

    List<Integer> getUserLike(@Param("user_id") Integer user_id);
}
