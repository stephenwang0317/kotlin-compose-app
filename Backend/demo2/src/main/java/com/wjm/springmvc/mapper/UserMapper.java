package com.wjm.springmvc.mapper;

import com.wjm.springmvc.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> listAllUsers();
    User searchUserById(@Param("id") Integer id);
    User login(@Param("id") Integer id, @Param("pwd") String pwd);
    Integer createUser(User user);
}
