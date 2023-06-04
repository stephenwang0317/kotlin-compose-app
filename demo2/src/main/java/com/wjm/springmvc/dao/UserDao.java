package com.wjm.springmvc.dao;

import com.wjm.springmvc.bean.User;

import java.util.List;

public interface UserDao {
    public User searchUserById(Integer id);
    public User createUser(User user);
    public List<User> listAllUsers();

    public User loginUser(User user);

}
