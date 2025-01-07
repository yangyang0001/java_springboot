package com.deepblue.inaction_00_spring.chapter_07.example_007_aspectj_named_pointcut;

import com.deepblue.inaction_00_spring.chapter_07.NaiveWaiter;
import com.deepblue.inaction_00_spring.chapter_07.Waiter;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 * 命名切点的使用!
 */
public class Main {

    public static void main(String[] args) {

        Waiter waiter = new NaiveWaiter();

        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(waiter);
        factory.addAspect(MineAspect.class);

        Waiter proxy = factory.getProxy();
        proxy.greetTo("zhangsan");
        System.out.println("------------------------------------------------------------------------------------------------------");
        proxy.serveTo("lisi");

    }
}
