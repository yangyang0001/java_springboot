package com.deepblue.inaction_00_spring.chapter_06.example_009_composable_pointcut_advisor;

import com.deepblue.inaction_00_spring.chapter_06.NaiveWaiter;
import com.deepblue.inaction_00_spring.chapter_06.Waiter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * 第5种切面: 复合切面 由 DefaultPointcutAdvisor 和 ComposablePointcut 配合实现
 * 本例子中 ComposablePointcut 又 并集了 两种切点
 */
public class Main {

    public static void main(String[] args) {

        ComposablePointcut composable = new ComposablePointcut();

        Pointcut pointcut1 = new ControlFlowPointcut(WatierDelegate.class, "service");

        NameMatchMethodPointcut pointcut2 = new NameMatchMethodPointcut();
        pointcut2.addMethodName("greetTo");

        composable = composable.intersection(pointcut1).intersection((Pointcut) pointcut2);

        GreetingBeforeAdvice advice = new GreetingBeforeAdvice();

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(composable);
        advisor.setAdvice(advice);

        Waiter waiter = new NaiveWaiter();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(waiter);
        factory.addAdvisor(advisor);

        Waiter proxy = (Waiter) factory.getProxy();

        WatierDelegate delegate = new WatierDelegate();
        // TODO 非常重要: 一定要使用 proxy 否则不起作用
        delegate.setWaiter(proxy);
        delegate.service("zhangsan");
    }
}
