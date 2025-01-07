package com.deepblue.inaction_00_spring.chapter_07_mine.example_002_aspectj_base;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        MineInterface object = new MineObject();

        AspectJProxyFactory factory = new AspectJProxyFactory();
        MineAspect aspect = new MineAspect();
        factory.setTarget(object);
        factory.addAspect(aspect);

        MineObject proxy = factory.getProxy();
        proxy.greet2("zhangsan");

        System.out.println("-------------------------------------------------------------------------------");
        proxy.serve2("lisi");

    }
}
