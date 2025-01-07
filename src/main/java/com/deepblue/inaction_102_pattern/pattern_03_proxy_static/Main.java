package com.deepblue.inaction_102_pattern.pattern_03_proxy_static;

import org.springframework.cglib.proxy.Enhancer;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Subject.class);
        enhancer.setCallback(new CommonMethodInterceptor(new RealSubject()));

        Subject subject = (Subject) enhancer.create();
        System.out.println(subject.sayHello("zhangsan123"));

    }
}
