package com.deepblue.inaction_00_spring.chapter_07.example_007_aspectj_named_pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 *
 */
@Aspect
public class MineAspect {

    @Before("MinePointcut.greetTo()")
    public void before() {
        System.out.println("mine aspect before method invoke!");
    }
}
