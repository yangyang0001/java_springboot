package com.deepblue.inaction_00_spring.chapter_07.example_003_aspectj_function_annotation;

import com.deepblue.inaction_00_spring.chapter_06.Waiter;
import com.deepblue.inaction_00_spring.chapter_07.example_001_annotation.Mine;

/**
 *
 */
public class AnnotationWaiter implements Waiter {

    @Override
    @Mine(value = true)
    public void greetTo(String name) {
        System.out.println("greet to " + name);
    }

    @Override
    public void serveTo(String name) {
        System.out.println("serving to " + name);
    }

}
