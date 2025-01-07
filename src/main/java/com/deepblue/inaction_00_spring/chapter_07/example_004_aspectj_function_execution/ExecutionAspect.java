package com.deepblue.inaction_00_spring.chapter_07.example_004_aspectj_function_execution;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 *
 */
@Aspect
public class ExecutionAspect {

    @AfterReturning("execution(* com.deepblue.inaction_00_spring.chapter_07.example_004_aspectj_function_execution.*Waiter.greetTo(..))")
    public void execution() {
        System.out.println("execution aspect execution method invoke!");
    }
}
