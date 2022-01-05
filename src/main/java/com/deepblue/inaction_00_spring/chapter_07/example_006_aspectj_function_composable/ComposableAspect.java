package com.deepblue.inaction_00_spring.chapter_07.example_006_aspectj_function_composable;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 *
 */
@Aspect
public class ComposableAspect {

    /**
     * ComposableWaiter 类中 greetTo(..) 方法 前置增强
     */
    @Before("execution(* greetTo(..)) && within(com.deepblue.inaction_00_spring.chapter_07.example_006_aspectj_function_composable.ComposableWaiter)")
    public void before() {
        System.out.println("composable aspect before method invoke!");
    }

    /**
     * Waiter 子类 和 Seller 类 中的任何方法都有 后置增强
     */
    @AfterReturning("target(com.deepblue.inaction_00_spring.chapter_07.Waiter) || target(com.deepblue.inaction_00_spring.chapter_07.example_006_aspectj_function_composable.Seller)")
    public void afterReturning() {
        System.out.println("composable aspect afterReturning method invoke!");
    }

}
