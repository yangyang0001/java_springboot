package com.deepblue.inaction_00_spring.chapter_06.example_007_dynamic_method_pointcut_advisor;

import com.deepblue.inaction_00_spring.chapter_06.NaiveWaiter;
import com.deepblue.inaction_00_spring.chapter_06.Waiter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 第3种切面: 动态方法切面 由 DefaultPointcutAdvisor 和 DynamicMethodMatcherPointcut 配合实现
 */
public class Main {

    public static void main(String[] args) {

        Waiter waiter = new NaiveWaiter();

        GreetingBeforeAdvice advice = new GreetingBeforeAdvice();
        GreetingDynamicMethodPointcut pointcut = new GreetingDynamicMethodPointcut();

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(advice);

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(waiter);
        factory.addAdvisor(advisor);

        Waiter proxy = (Waiter) factory.getProxy();
        proxy.greetTo("zhangsan");
        proxy.serveTo("lisi");

    }
}
