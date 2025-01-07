package com.deepblue.inaction_00_spring.chapter_07.example_006_aspectj_function_composable;


import com.deepblue.inaction_00_spring.chapter_07.Waiter;

/**
 *
 */
public class ComposableWaiter implements Waiter {

    @Override
    public void greetTo(String name) {
        System.out.println("greet to " + name);
    }

    @Override
    public void serveTo(String name) {
        System.out.println("serving to " + name);
    }

}
