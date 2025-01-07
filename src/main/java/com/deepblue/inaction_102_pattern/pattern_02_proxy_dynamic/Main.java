package com.deepblue.inaction_102_pattern.pattern_02_proxy_dynamic;

import java.lang.reflect.Proxy;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        ProxyHandler handler = new ProxyHandler(new RealSubject());

        Subject subject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{Subject.class}, handler);

        System.out.println(subject.sayHello("Yang"));

    }
}
