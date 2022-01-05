package com.deepblue.inaction_00_spring.chapter_06.example_003_method_interceptor;

import com.deepblue.inaction_00_spring.chapter_06.NaiveWaiter;
import com.deepblue.inaction_00_spring.chapter_06.Waiter;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 第3种增强: 环绕增强, 没有设置 切点位置, 对当前 target 中的所有方法有效
 */
public class Main {

    public static void main(String[] args) {

        Waiter waiter = new NaiveWaiter();
        MethodInterceptor interceptor = new GreetingMethodInterceptor();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(waiter);
        factory.addAdvice(interceptor);

        Waiter proxy = (Waiter) factory.getProxy();
        proxy.greetTo("zhangsan");
        System.out.println("------------------------------------------------------------------------------------------------------");
        proxy.serveTo("lisi");

    }
}
