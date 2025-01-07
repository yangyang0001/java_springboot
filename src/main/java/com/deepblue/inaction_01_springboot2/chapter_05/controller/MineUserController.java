package com.deepblue.inaction_01_springboot2.chapter_05.controller;

import com.deepblue.inaction_01_springboot2.chapter_05.dao.UserDao;
import com.deepblue.inaction_01_springboot2.chapter_05.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 */
@Controller
public class MineUserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/getUserCount")
    @ResponseBody
    public Long getUserCount() {
        return userDao.getUserCount();
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUserById(Long userId) {
        return userDao.getUser(userId);
    }

    @RequestMapping("/getUserListByDeptId")
    @ResponseBody
    public List<User> getUserListByDeptId(Long departmentId) {
        return userDao.getUserListByDeptId(departmentId);
    }
}
