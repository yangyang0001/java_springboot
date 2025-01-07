package com.deepblue.inaction_00_spring.chapter_07.example_002_aspectj_base;

import com.deepblue.inaction_00_spring.chapter_07.NaiveWaiter;
import com.deepblue.inaction_00_spring.chapter_07.Waiter;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 * 引入 AspectJ 的一个例子, 以便直观了解 @Aspect
 */
public class Main {

    public static void main(String[] args) {

        Waiter waiter = new NaiveWaiter();

        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(waiter);
        factory.addAspect(PreGreetingAspect.class);

        Waiter proxy = factory.getProxy();
        proxy.greetTo("zhangsan");
        proxy.serveTo("lisi");
    }
}















