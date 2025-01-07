package com.deepblue.inaction_00_spring.chapter_07;

/**
 *
 */
public class NaiveWaiter implements Waiter {

    @Override
    public void greetTo(String name) {
        System.out.println("greet to " + name);
    }

    @Override
    public void serveTo(String name) {
        System.out.println("serving to " + name);
    }

}
