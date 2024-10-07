package com.deepblue.inaction_00_spring.chapter_06_mine.example_005_staticmethod_matcher_pointcut_advisor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 *
 */
public class MineStaticMethodAdvisor extends StaticMethodMatcherPointcutAdvisor {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("MineStaticMethodAdvisor matches method      is :" + method);
        System.out.println("MineStaticMethodAdvisor matches targetClass is :" + targetClass);

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
