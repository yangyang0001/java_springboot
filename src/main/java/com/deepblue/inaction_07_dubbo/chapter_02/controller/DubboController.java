package com.deepblue.inaction_07_dubbo.chapter_02.controller;


import com.deepblue.api.EchoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
public class DubboController {

    @Reference
    private EchoService echoService;

    @RequestMapping("/invokeEcho")
    @ResponseBody
    public String invokeEchoMethod(String username) {
        return echoService.sayHello(username);
    }
}
