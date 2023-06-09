package com.wjm.springmvc.service;

import com.wjm.springmvc.bean.User;
import com.wjm.springmvc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;
    @Override
    public User getUserById(Integer id) {
        return userDao.searchUserById(id);
    }

    @Override
    public User createUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.listAllUsers();
    }

    @Override
    public User login(User user) {
        User queryUser = userDao.loginUser(user);
        if(queryUser == null) {
            return new User();
        }else {
            return queryUser;
        }
    }

    @Override
    public User changeUserInfo(User user) {
        int rows = userDao.changeInfo(user);
        if (rows == 0) {
            return new User();
        } else {
            return userDao.searchUserById(user.getUser_id());
        }
    }
}
