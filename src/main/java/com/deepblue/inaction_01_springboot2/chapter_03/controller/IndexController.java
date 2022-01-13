package com.deepblue.inaction_01_springboot2.chapter_03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        return "hello world springboot, now is :" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
