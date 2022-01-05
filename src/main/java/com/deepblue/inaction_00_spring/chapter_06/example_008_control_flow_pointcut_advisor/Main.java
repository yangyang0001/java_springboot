package com.deepblue.inaction_00_spring.chapter_06.example_008_control_flow_pointcut_advisor;

import com.deepblue.inaction_00_spring.chapter_06.NaiveWaiter;
import com.deepblue.inaction_00_spring.chapter_06.Waiter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 第4种切面: 流程切面 由 DefaultPointcutAdvisor 和 ControlFlowPointcut 配合实现
 */
public class Main {

    public static void main(String[] args) {


        Waiter waiter = new NaiveWaiter();
        GreetingBeforeAdvice advice = new GreetingBeforeAdvice();
        GreetingControlFlowPointcut pointcut = new GreetingControlFlowPointcut(WatierDelegate.class, "service");

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(advice);

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
