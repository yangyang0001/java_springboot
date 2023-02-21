package com.deepblue.inaction_102_pattern.pattern_02_proxy_dynamic;

/**
 *
 */
public class RealSubject implements Subject{

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

}
