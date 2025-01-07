package com.deepblue.inaction_00_spring.chapter_06_mine.example_006_regexpmethod_pointcut_advisor;

/**
 *
 */
public class MineObject implements MineInterface {

    @Override
    public void greet2(String name) {
        System.out.println("greet2 method invoke, name is :" + name);
    }

    @Override
    public void serve2(String name) {
        System.out.println("serve2 method invoke, name is :" + name);
    }

}
