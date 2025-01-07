package com.deepblue.inaction_00_spring.chapter_07.example_005_aspectj_function_within;

import com.deepblue.inaction_00_spring.chapter_06.Waiter;

/**
 *
 */
public class WithInWaiter implements Waiter {

    @Override
    public void greetTo(String name) {
        System.out.println("within waiter greet to " + name);
    }

    @Override
    public void serveTo(String name) {
        System.out.println("within waiter serving to " + name);
    }

}
