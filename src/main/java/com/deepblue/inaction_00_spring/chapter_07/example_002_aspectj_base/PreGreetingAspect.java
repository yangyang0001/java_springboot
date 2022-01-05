package com.deepblue.inaction_00_spring.chapter_07.example_002_aspectj_base;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 *
 */
@Aspect
public class PreGreetingAspect {

    @Before("execution(* greetTo(..))")
    public void beforeGreeting() {
        System.out.println("How are you!");
    }
}
