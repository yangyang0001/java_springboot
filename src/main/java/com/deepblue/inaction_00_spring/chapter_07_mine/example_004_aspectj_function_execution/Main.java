package com.deepblue.inaction_00_spring.chapter_07_mine.example_004_aspectj_function_execution;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        MineObject object = new MineObject();
        ExecutionAspect aspect = new ExecutionAspect();

        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(object);
        factory.addAspect(aspect);

        MineObject proxy = factory.getProxy();

        proxy.greet2("zhangsan");

        System.out.println("-------------------------------------------------------------------");

        proxy.serve2("lisi");


    }
}
