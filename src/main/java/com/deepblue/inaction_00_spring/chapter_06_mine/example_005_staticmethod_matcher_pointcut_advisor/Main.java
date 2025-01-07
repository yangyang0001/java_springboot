package com.deepblue.inaction_00_spring.chapter_06_mine.example_005_staticmethod_matcher_pointcut_advisor;

import org.springframework.aop.framework.ProxyFactory;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        MineObject object = new MineObject();
        MineStaticMethodAdvisor advisor = new MineStaticMethodAdvisor();
        MineMethodInterceptor interceptor = new MineMethodInterceptor();
        advisor.setAdvice(interceptor);

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(object);
        factory.addAdvisor(advisor);

        MineObject proxy = (MineObject) factory.getProxy();
        proxy.greet2("zhangsan");

        System.out.println("--------------------------------------------------");

        proxy.serve2("lisi");


    }
}
