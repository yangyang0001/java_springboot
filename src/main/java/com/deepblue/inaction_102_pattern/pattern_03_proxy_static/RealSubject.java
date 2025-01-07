package com.deepblue.inaction_102_pattern.pattern_03_proxy_static;

/**
 *
 */
public class RealSubject implements Subject {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
