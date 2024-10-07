package com.deepblue.inaction_00_spring.chapter_06_mine.example_001_methodbefore_advice;

import org.springframework.aop.framework.ProxyFactory;

/**
 * 第1种增强: 前置增强, 没有设置 切点位置, 对当前 target 中的所有方法有效
 */
public class Main {

    public static void main(String[] args) {

        MineBefore before = new MineBefore();
        MineBeforeAdvice beforeAdvice = new MineBeforeAdvice();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(before);
        factory.addAdvice(beforeAdvice);

        MineBefore proxy = (MineBefore) factory.getProxy();
        proxy.greet2("zhangsan");

        System.out.println("------------------------------------------------------");

        proxy.serve2("lisi");

        System.out.println("------------------------------------------------------");
        // 没有 代理就没有 前置增强这一说!
        before.greet2("wangwu");
        before.serve2("zhaoliu");

    }
}
