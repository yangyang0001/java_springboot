package com.deepblue.inaction_00_spring.chapter_06;

import com.deepblue.inaction_00_spring.chapter_06.Waiter;

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
