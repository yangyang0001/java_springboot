package com.deepblue.inaction_00_spring.chapter_09.example_003;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
public class MineController {

    @Autowired
    private MineService mineService;

    @Autowired
    private NineService nineService;


    @RequestMapping("/test_same_00")
    public String test_same_00() {
        return mineService.test_same_00();
    }

    public String test_same_01() {

        return "success";
    }

    public String test_same_10() {

        return "success";
    }

    public String test_same_11() {

        return "success";
    }


    public String test_diff_00() {

        return "success";
    }

    public String test_diff_01() {

        return "success";
    }

    public String test_diff_10() {

        return "success";
    }

    public String test_diff_11() {

        return "success";
    }

}
