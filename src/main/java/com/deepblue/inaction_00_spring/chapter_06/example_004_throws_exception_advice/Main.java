package com.deepblue.inaction_00_spring.chapter_06.example_004_throws_exception_advice;

import com.deepblue.inaction_00_spring.chapter_06.NaiveWaiter;
import com.deepblue.inaction_00_spring.chapter_06.Waiter;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 第4种增强: 抛出异常增强, 没有设置 切点位置, 对当前 target 中的所有方法有效
 */
public class Main {

    public static void main(String[] args) {

        Waiter waiter = new ExceptionWaiter();
        ThrowsAdvice advice = new GreetingExceptionAdvice();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(waiter);
        factory.addAdvice(advice);

        Waiter proxy = (Waiter) factory.getProxy();
        proxy.greetTo("zhangsan");
        System.out.println("------------------------------------------------------------------------------------------------------");
        proxy.serveTo("lisi");

    }
}
