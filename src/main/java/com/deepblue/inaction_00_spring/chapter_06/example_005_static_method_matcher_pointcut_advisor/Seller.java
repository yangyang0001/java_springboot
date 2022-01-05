package com.deepblue.inaction_00_spring.chapter_06.example_005_static_method_matcher_pointcut_advisor;

/**
 *
 */
public class Seller {

    public void greetTo(String name) {
        System.out.println("greet to " + name);
    }

    public void serveTo(String name) {
        System.out.println("serving to " + name);
    }

}
