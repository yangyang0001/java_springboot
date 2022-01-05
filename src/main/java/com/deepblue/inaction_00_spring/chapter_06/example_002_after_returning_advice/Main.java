package com.deepblue.inaction_00_spring.chapter_06.example_002_after_returning_advice;

import com.deepblue.inaction_00_spring.chapter_06.NaiveWaiter;
import com.deepblue.inaction_00_spring.chapter_06.Waiter;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 第2种增强: 后置增强, 没有设置 切点位置, 对当前 target 中的所有方法有效
 */
public class Main {

    public static void main(String[] args) {

        Waiter waiter = new NaiveWaiter();
        AfterReturningAdvice after = new GreetingAfterAdvice();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(waiter);
        factory.addAdvice(after);

        Waiter proxy = (Waiter) factory.getProxy();
        proxy.greetTo("zhangsan");
        System.out.println("------------------------------------------------------------------------------------------------------");
        proxy.serveTo("lisi");

    }
}
