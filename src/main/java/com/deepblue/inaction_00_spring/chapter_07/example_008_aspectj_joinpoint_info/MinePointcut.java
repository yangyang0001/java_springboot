package com.deepblue.inaction_00_spring.chapter_07.example_008_aspectj_joinpoint_info;

import org.aspectj.lang.annotation.Pointcut;

/**
 *
 */
public class MinePointcut {

    @Pointcut("execution(* getBook(..))")
    public void getBook() {}

    @Pointcut("@annotation(Mine)")
    public void mine() {}
}
