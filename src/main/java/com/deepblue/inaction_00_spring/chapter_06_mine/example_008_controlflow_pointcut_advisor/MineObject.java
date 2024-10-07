package com.deepblue.inaction_00_spring.chapter_06_mine.example_008_controlflow_pointcut_advisor;

/**
 *
 */
public class MineObject implements MineInterface {

    @Override
    public String greet2(String name) {
        System.out.println("greet2 method invoke, name is " + name);
        return "greet2 " + name;
    }

    @Override
    public String serve2(String name) {
        System.out.println("serve2 method invoke, name is " + name);
        return "serve2 " + name;
    }
}
