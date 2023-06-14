package com.wjm.springmvc.service;

import com.wjm.springmvc.bean.User;
import com.wjm.springmvc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.searchUserById(id);
    }

    @Override
    public User createUser(User user) {
        Integer i = userMapper.createUser(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.listAllUsers();
    }

    @Override
    public User login(User user) {
        User queryUser = userMapper.login(user.getUser_id(), user.getUser_pwd());
        if (queryUser == null) {
            return new User();
        } else {
            return queryUser;
        }
    }

    @Override
    public User changeUserInfo(User user) {
        int rows = userMapper.changeInfo(user);
        if (rows == 0) {
            return new User();
        } else {
            return userMapper.searchUserById(user.getUser_id());
        }
    }
}
