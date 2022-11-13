package com.deepblue.inaction_102_pattern.pattern_03_proxy_static;

import org.springframework.cglib.proxy.Enhancer;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealSubejct.class);
        enhancer.setCallback(new CommonMethodInterceptor());
        RealSubejct subject = (RealSubejct) enhancer.create();

        System.out.println(subject.sayHello("PatternHandler"));

    }
}
