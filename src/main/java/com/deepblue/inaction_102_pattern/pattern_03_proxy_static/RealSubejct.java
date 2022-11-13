package com.deepblue.inaction_102_pattern.pattern_03_proxy_static;

/**
 *
 *
 */
public class RealSubejct implements Subject{

    @Override
    public String sayHello(String username) {
        return "hello " + username;
    }
}
