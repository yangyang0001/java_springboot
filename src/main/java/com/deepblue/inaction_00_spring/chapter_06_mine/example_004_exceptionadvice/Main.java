package com.deepblue.inaction_00_spring.chapter_06_mine.example_004_exceptionadvice;

import org.springframework.aop.framework.ProxyFactory;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        MineObject object = new MineObject();
        MineExceptionAdvice advice = new MineExceptionAdvice();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(object);
        factory.addAdvice(advice);


        MineObject proxy = (MineObject) factory.getProxy();

//        proxy.greet2();
        proxy.serve2();

    }
}
