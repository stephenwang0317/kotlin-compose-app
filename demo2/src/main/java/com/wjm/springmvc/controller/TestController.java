package com.wjm.springmvc.controller;

import com.wjm.springmvc.bean.User;
import com.wjm.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class TestController {
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String getIndex() {
        return "index";
    }

    @RequestMapping("/testServletAPI")
    public String testServletAPI(HttpServletRequest request) {
        request.setAttribute("info", "方法: ServletAPI");
        return "target";
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("info", "方法: ModelAndView");
        mv.setViewName("target");
        return mv;
    }

    @RequestMapping("/testModel")
    public String testServletAPI(Model m) {
        m.addAttribute("info", "方法: Model");
        return "target";
    }

    @RequestMapping("/testMap")
    public String testMap(Map<String, Object> map) {
        map.put("info", "方法: Map");
        return "target";
    }

    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap map) {
        map.addAttribute("info", "方法: ModelMap");
        return "target";
    }

    @RequestMapping("/testSession")
    public String testSession(HttpSession session) {
        Date date = new Date();
        Object a = session.getAttribute("info");
        if (a == null)
            session.setAttribute("info", date.getTime());

        return "target2";
    }

    @RequestMapping("/testApplication")
    public String testApplication(HttpSession session) {
        ServletContext sc = session.getServletContext();
        Date date = new Date();
        Object a = sc.getAttribute("info");
        if (a == null)
            sc.setAttribute("info", date.getTime());

        return "target2";
    }

    @RequestMapping("/testForward")
    public String testForward() {
        return "forward:/";
    }

    @RequestMapping("/testRedirect")
    public String testRedirect() {
        return "redirect:/";
    }

    @RequestMapping("/success2")
    @ResponseBody
    public String testResponseBody() {
        return "<h1 style=\"color: blue\">success2";
    }

    @RequestMapping("/hello")
    public String getHello() {
        return "hello";
    }

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
}
