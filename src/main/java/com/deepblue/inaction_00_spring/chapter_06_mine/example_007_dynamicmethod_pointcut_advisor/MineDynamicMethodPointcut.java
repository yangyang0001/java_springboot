package com.deepblue.inaction_00_spring.chapter_06_mine.example_007_dynamicmethod_pointcut_advisor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 *
 */
public class MineDynamicMethodPointcut extends DynamicMethodMatcherPointcut {


    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        System.out.println("MineDynamicMethodPointcut matches method invoke, method is :" + method.getName());
        boolean matchFlag = "greet2".equals(method.getName());
        return matchFlag;
    }

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                // 表示只能是 MineInterface.class 或者 其子类
                return MineInterface.class.isAssignableFrom(clazz);
            }
        };
    }
}
