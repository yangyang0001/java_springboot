package com.deepblue.inaction_00_spring.chapter_06_mine.example_008_controlflow_pointcut_advisor;

import org.springframework.aop.support.ControlFlowPointcut;

/**
 *
 */
public class MineControlFlowPointcut extends ControlFlowPointcut {

    public MineControlFlowPointcut(Class<?> clazz, String methodName) {
        super(clazz, methodName);
    }
}
