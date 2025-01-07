package com.deepblue.inaction_00_spring.chapter_07_mine.example_004_aspectj_function_execution;

/**
 *
 */
public class MineObject implements MineInterface {

    @Override
    public String greet2(String name) {
        System.out.println("greet2 method invoke, param name is " + name);
        return "greet2 " + name;
    }

    @Override
    public String serve2(String name) {
        System.out.println("serve2 method invoke, param name is " + name);
        return "serve2 " + name;
    }
}