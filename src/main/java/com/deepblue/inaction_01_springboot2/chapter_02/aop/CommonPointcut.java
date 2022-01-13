package com.deepblue.inaction_01_springboot2.chapter_02.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 *
 */
public class CommonPointcut {

    @Pointcut("@within(org.springframework.stereotype.Controller) && within(com.deepblue.inaction_01_springboot2.chapter_01.controller.*)")
    public void controller() {}
}
