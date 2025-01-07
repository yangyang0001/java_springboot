package com.deepblue.inaction_00_spring.chapter_06.example_001_method_before_advice;

import com.deepblue.inaction_00_spring.chapter_06.NaiveWaiter;
import com.deepblue.inaction_00_spring.chapter_06.Waiter;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 第1种增强: 前置增强, 没有设置 切点位置, 对当前 target 中的所有方法有效
 */
public class Main {

    public static void main(String[] args) {

        Waiter target = new NaiveWaiter();
        MethodBeforeAdvice before = new GreetingBeforeAdvice();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(before);

        Waiter proxy = (Waiter) factory.getProxy();
        proxy.greetTo("zhangsan");
        System.out.println("------------------------------------------------------------------------------------------------------");
        proxy.serveTo("lisi");

    }
}
