package com.deepblue.inaction_00_spring.chapter_07.example_007_aspectj_named_pointcut;

import org.aspectj.lang.annotation.Pointcut;

/**
 *
 */
public class MinePointcut {

    @Pointcut("execution(* greetTo(..))")
    public void greetTo() {}
}
