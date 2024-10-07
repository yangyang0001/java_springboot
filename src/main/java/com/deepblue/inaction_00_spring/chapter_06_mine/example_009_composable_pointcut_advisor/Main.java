package com.deepblue.inaction_00_spring.chapter_06_mine.example_009_composable_pointcut_advisor;

import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        MineObject object = new MineObject();
        ComposablePointcut composablePointcut = new ComposablePointcut();

        MineControlFlowPointcut controlFlowPointcut = new MineControlFlowPointcut(MineObjectDelegate.class, "service");
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.setMappedName("greet2");

        composablePointcut = composablePointcut.intersection((Pointcut) controlFlowPointcut).intersection((Pointcut) nameMatchMethodPointcut);

        MineMethodInterceptor interceptor = new MineMethodInterceptor();

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(composablePointcut);
        advisor.setAdvice(interceptor);

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(object);
        factory.addAdvisor(advisor);

        MineObjectDelegate delegate = new MineObjectDelegate();
        MineObject proxy = (MineObject) factory.getProxy();

        delegate.setMineInterface(proxy);

        delegate.service("zhangsan");

    }
}
