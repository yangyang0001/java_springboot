package com.deepblue.inaction_00_spring.chapter_06.example_008_control_flow_pointcut_advisor;

import org.springframework.aop.support.ControlFlowPointcut;

/**
 *
 */
public class GreetingControlFlowPointcut extends ControlFlowPointcut {

    public GreetingControlFlowPointcut(Class<?> clazz) {
        super(clazz);
    }

    public GreetingControlFlowPointcut(Class<?> clazz, String methodName) {
        super(clazz, methodName);
    }
}
