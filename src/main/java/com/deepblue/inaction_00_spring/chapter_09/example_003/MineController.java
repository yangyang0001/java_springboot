package com.deepblue.inaction_00_spring.chapter_09.example_003;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 方法A 调用 方法B 事务总结:
 * 1、如果 只有A加 @Transactional注解; 则AB在同一事务中;
 * 2、如果 只有B加 @Transactional注解; AB方法为同一类, 事务失效; AB不同类, 只有B有事务;
 */
@RestController
public class MineController {

    @Autowired
    private HineService hineService;

    @Autowired
    private NineService nineService;

    /**
     * 验证了 同一个类中 没有事务A 调用 没有事务B 的情况下, AB都不能回滚!
     * @return
     */
    @RequestMapping("/test_same_00")
    public String test_same_00() {
        return hineService.saveMine0().toString();
    }

    /**
     * 验证了 同一个类中 没有事务A 调用 有事务B 的情况下, AB都不能回滚!
     * @return
     */
    @RequestMapping("/test_same_01")
    public String test_same_01() {
        return hineService.saveMine0().toString();
    }

    /**
     * 验证了 同一个类中 有事务A 调用 没有事务B 的情况下, AB都进行回滚!
     * @return
     */
    @RequestMapping("/test_same_10")
    public String test_same_10() {
        return hineService.saveMine0().toString();
    }

    /**
     * 验证了 同一个类中 有事务A 调用 有事务B 的情况下, AB都进行回滚!
     * @return
     */
    @RequestMapping("/test_same_11")
    public String test_same_11() {
        return hineService.saveMine0().toString();
    }

    /**
     * 验证了 不同的类中 没有事务A 调用 没有事务B 的情况下, AB都不能回滚!
     * @return
     */
    @RequestMapping("/test_diff_00")
    public String test_diff_00() {
        return hineService.saveMine0().toString();
    }

    /**
     * 验证了 不同的类中 没有事务A 调用 有事务B 的情况下, A不能回滚, B可以回滚!
     * @return
     */
    @RequestMapping("/test_diff_01")
    public String test_diff_01() {
        return hineService.saveMine0().toString();
    }

    /**
     * 验证了 不同的类中 有事务A 调用 没有事务B 的情况下, AB都能回滚!
     * @return
     */
    @RequestMapping("/test_diff_10")
    public String test_diff_10() {
        return hineService.saveMine0().toString();
    }

    /**
     * 验证了 不同的类中 有事务A 调用 有事务B 的情况下, AB都能回滚!
     * @return
     */
    @RequestMapping("/test_diff_11")
    public String test_diff_11() {
        return hineService.saveMine0().toString();
    }

}
