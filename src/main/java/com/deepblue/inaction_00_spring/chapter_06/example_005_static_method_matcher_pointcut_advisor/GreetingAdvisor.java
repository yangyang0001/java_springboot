package com.deepblue.inaction_00_spring.chapter_06.example_005_static_method_matcher_pointcut_advisor;

import com.deepblue.inaction_00_spring.chapter_06.Waiter;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * 第1种切点: 静态普通方法名 匹配 切面
 */
public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("GreetingAdvisor matches method      is :" + method);
        System.out.println("GreetingAdvisor matches targetClass is :" + targetClass);

        boolean matchFlag = "greetTo".equals(method.getName());
        return matchFlag;
    }

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                // 表示只能是 Waiter.class 或者 其子类
                return Waiter.class.isAssignableFrom(clazz);
            }
        };
    }
}
