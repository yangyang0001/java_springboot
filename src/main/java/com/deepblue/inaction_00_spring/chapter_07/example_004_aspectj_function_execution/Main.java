package com.deepblue.inaction_00_spring.chapter_07.example_004_aspectj_function_execution;

import com.deepblue.inaction_00_spring.chapter_06.Waiter;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Waiter waiter1 = new ExecutionFirstWaiter();
        Waiter waiter2 = new ExecutionSecndWaiter();

        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(waiter2);
        factory.addAspect(ExecutionAspect.class);

        Waiter proxy = factory.getProxy();
        proxy.greetTo("zhangsan");
        System.out.println("------------------------------------------------------------------------------------------------------");
        proxy.serveTo("lisi");

        System.out.println("\n******************************************************************************************************\n");
        waiter2.greetTo("zhangsan");
        System.out.println("------------------------------------------------------------------------------------------------------");
        waiter2.serveTo("lisi");

    }
}
