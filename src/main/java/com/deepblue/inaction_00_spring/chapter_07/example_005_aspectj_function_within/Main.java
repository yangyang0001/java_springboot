package com.deepblue.inaction_00_spring.chapter_07.example_005_aspectj_function_within;

import com.deepblue.inaction_00_spring.chapter_06.Waiter;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Waiter waiter = new WithInWaiter();

        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(waiter);
        factory.addAspect(WithInAspect.class);

        Waiter proxy = factory.getProxy();
        proxy.greetTo("zhangsan");
        System.out.println("------------------------------------------------------------------------------------------------------");
        proxy.serveTo("lisi");

    }
}
