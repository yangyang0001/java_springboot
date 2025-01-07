package com.deepblue.inaction_01_springboot2.chapter_16;

import com.deepblue.inaction_01_springboot2.chapter_16.example_01_exclusive_lock_curator.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order")
    public String order(String type) {
        orderService.makeOrderType(type);
        return "success";
    }


}
