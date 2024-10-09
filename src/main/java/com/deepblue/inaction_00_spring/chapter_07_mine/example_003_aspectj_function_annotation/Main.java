package com.deepblue.inaction_00_spring.chapter_07_mine.example_003_aspectj_function_annotation;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        MineObject object = new MineObject();

        MineAspect mineAspect = new MineAspect();

        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(object);
        factory.addAspect(mineAspect);

        MineObject proxy = factory.getProxy();

        proxy.greet2("zhangsan");

        System.out.println("-----------------------------------------------------------------------------------------");

        proxy.serve2("lisi");
    }
}
