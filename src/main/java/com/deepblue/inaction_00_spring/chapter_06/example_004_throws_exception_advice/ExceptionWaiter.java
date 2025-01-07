package com.deepblue.inaction_00_spring.chapter_06.example_004_throws_exception_advice;

import com.deepblue.inaction_00_spring.chapter_06.Waiter;

/**
 *
 */
public class ExceptionWaiter implements Waiter {
    @Override
    public void greetTo(String name) {
        int i = 10 / 0;
        System.out.println("greet to " + name);
        throw new RuntimeException("greet to runtime exception");
    }

    @Override
    public void serveTo(String name) {
        name = null;
        name.equals("zhangsan");
        System.out.println("serving to " + name);
    }
}
