package com.deepblue.inaction_00_spring.chapter_07_mine.example_005_pointcut_namespace;

import org.aspectj.lang.annotation.Pointcut;

/**
 *
 */
public class MinePointcut {

    @Pointcut("@annotation(com.deepblue.inaction_00_spring.chapter_07_mine.example_005_pointcut_namespace.Mine)")
    public void selectId(){}

    @Pointcut("execution(* deleteId(..))")
    public void deleteId(){}

}
