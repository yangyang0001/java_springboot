package com.deepblue.inaction_00_spring.chapter_07.example_004_aspectj_function_execution;

import com.deepblue.inaction_00_spring.chapter_06.Waiter;
import com.deepblue.inaction_00_spring.chapter_07.example_001_annotation.Mine;

/**
 *
 */
public class ExecutionFirstWaiter implements Waiter {

    @Override
    public void greetTo(String name) {
        System.out.println("execution first waiter greet to " + name);
    }

    @Override
    public void serveTo(String name) {
        System.out.println("execution first waiter serving to " + name);
    }

}
