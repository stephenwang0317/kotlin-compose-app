package com.wjm.springmvc.service;

import com.wjm.springmvc.bean.User;

import java.util.List;

public interface UserService {

    public User getUserById(Integer id);
    public User createUser(User user);

    public List<User> getAllUsers();

    public User login(User user);

    public User changeUserInfo(User user);
}
