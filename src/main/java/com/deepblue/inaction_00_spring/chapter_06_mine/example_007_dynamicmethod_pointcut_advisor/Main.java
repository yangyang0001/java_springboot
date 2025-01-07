package com.deepblue.inaction_00_spring.chapter_06_mine.example_007_dynamicmethod_pointcut_advisor;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        MineObject object = new MineObject();

        MineMethodInterceptor interceptor = new MineMethodInterceptor();
        MineDynamicMethodPointcut pointcut = new MineDynamicMethodPointcut();

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(object);
        factory.addAdvisor(advisor);

        MineObject proxy = (MineObject) factory.getProxy();
        proxy.greet2("zhangsan");

        System.out.println("--------------------------------------------------------------------------------------------");

        proxy.serve2("lisi");

    }
}
