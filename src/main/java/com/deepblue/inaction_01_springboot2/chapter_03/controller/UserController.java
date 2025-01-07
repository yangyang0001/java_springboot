package com.deepblue.inaction_01_springboot2.chapter_03.controller;

import com.deepblue.inaction_01_springboot2.chapter_03.entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 */
@Controller
public class UserController {

    @RequestMapping("/user/getUser")
    @ResponseBody
    public User getUser(long userId) {
        User user = new User();
        user.setUserId(userId);
        user.setUsername("zhangsan");
        user.setPassword("123456");

        return user;
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response, String username, String password) {
        long userId = ThreadLocalRandom.current().nextLong(10000L);
        User user = new User(userId, username, password);
        request.getSession().setAttribute("user", user);

        return "login success";
    }

    @RequestMapping("/format")
    @ResponseBody
    public String format(String date) {
        return date;
    }
}
