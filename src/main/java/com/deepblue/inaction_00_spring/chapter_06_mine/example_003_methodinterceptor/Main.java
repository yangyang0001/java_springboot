package com.deepblue.inaction_00_spring.chapter_06_mine.example_003_methodinterceptor;

import org.springframework.aop.framework.ProxyFactory;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        MineObject object = new MineObject();
        MineMethodInterceptor interceptor = new MineMethodInterceptor();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(object);
        factory.addAdvice(interceptor);

        MineObject proxy = (MineObject) factory.getProxy();

        proxy.greet2("zhangsan");

        System.out.println("------------------------------------------------------");

        proxy.serve2("lisi");

    }
}
