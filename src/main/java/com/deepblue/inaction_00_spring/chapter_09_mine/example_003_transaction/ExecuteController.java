package com.deepblue.inaction_00_spring.chapter_09_mine.example_003_transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class ExecuteController {

    @Autowired
    private ExecuteService executeService;

    @RequestMapping("/add_00")
    public String add_00() {
        return executeService.add00();
    }

    @RequestMapping("/add_01")
    public String add_01() {
        return executeService.add01();
    }

    @RequestMapping("/add_10")
    public String add_10() {
        return executeService.add10();
    }

    @RequestMapping("/add_11")
    public String add_11() {
        return executeService.add11();
    }


}
