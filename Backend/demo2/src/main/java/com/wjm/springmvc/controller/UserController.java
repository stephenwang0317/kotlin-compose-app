package com.wjm.springmvc.controller;

import com.wjm.springmvc.bean.User;
import com.wjm.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    @ResponseBody
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    @ResponseBody
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/user/login")
    @ResponseBody
    public User login(@RequestBody User user) {
        return userService.login(user);
    }

    @PutMapping("/user")
    @ResponseBody
    public User changeUserInfo(@RequestBody User user) {
        return userService.changeUserInfo(user);
    }
}
