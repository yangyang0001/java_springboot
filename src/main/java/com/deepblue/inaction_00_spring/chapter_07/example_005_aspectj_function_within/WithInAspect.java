package com.deepblue.inaction_00_spring.chapter_07.example_005_aspectj_function_within;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 *
 */
@Aspect
public class WithInAspect {

    @Before("within(com.deepblue.inaction_00_spring.chapter_07.example_005_aspectj_function_within.WithInWaiter)")
    public void within() {
        System.out.println("within aspect within method invoke!");
    }
}
