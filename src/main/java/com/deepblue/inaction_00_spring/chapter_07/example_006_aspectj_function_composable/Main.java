package com.deepblue.inaction_00_spring.chapter_07.example_006_aspectj_function_composable;

import com.deepblue.inaction_00_spring.chapter_07.NaiveWaiter;
import com.deepblue.inaction_00_spring.chapter_07.Waiter;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Waiter waiter1 = new NaiveWaiter();
        Waiter waiter2 = new ComposableWaiter();

        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(waiter2);
        factory.addAspect(ComposableAspect.class);

        Waiter proxy = factory.getProxy();
        proxy.greetTo("zhangsan");
        System.out.println("------------------------------------------------------------------------------------------------------");
        proxy.serveTo("lisi");

    }
}
