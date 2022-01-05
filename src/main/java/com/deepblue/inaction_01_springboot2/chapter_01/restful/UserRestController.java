package com.deepblue.inaction_01_springboot2.chapter_01.restful;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class UserRestController {

    @RequestMapping("/getUserLevel/{userId}")
    public Integer getUserLevel(@PathVariable String userId) {
        // 模拟用户等级
        return 3;
    }


}
