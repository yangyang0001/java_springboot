package com.deepblue.inaction_00_spring.chapter_06_mine.example_008_controlflow_pointcut_advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        MineObject object = new MineObject();

        MineMethodInterceptor interceptor = new MineMethodInterceptor();
        MineControlFlowPointcut pointcut = new MineControlFlowPointcut(MineObjectDelegate.class, "service");
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(object);
        factory.addAdvisor(advisor);

        MineObject proxy = (MineObject) factory.getProxy();
        MineObjectDelegate delegate = new MineObjectDelegate();
        delegate.setMineInterface(proxy);

        delegate.service("zhangsan");

    }
}
