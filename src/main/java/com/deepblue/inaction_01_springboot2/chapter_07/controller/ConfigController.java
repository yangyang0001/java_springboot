package com.deepblue.inaction_01_springboot2.chapter_07.controller;

import com.deepblue.inaction_01_springboot2.chapter_05.entity.User;
import com.deepblue.inaction_01_springboot2.chapter_07.config.MineConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
public class ConfigController {

    @Autowired
    private MineConfig config;

    @Autowired
    private User mineUser;

    @RequestMapping("/getVersion")
    @ResponseBody
    public String getVersion() {
        return config.getVersion();
    }

    @RequestMapping("/getConfigUser")
    @ResponseBody
    public User getConfigUser() {
        return mineUser;
    }
}
