package com.deepblue.inaction_01_springboot2.chapter_01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
public class HelloWorldController {

    @RequestMapping("/sayHello")
    @ResponseBody
    public String sayHello(String name) {
        return "Hello " + name;
    }

}
