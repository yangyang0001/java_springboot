package com.deepblue.inaction_00_spring.chapter_06.example_007_dynamic_method_pointcut_advisor;

import com.alibaba.fastjson.JSON;
import com.deepblue.inaction_00_spring.chapter_06.Waiter;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * TODO 使用规则:
 *      先使用 静态切点 检查
 *          如果满足 则继续执行其他代码
 *          若不满足 则进行动态切点检查
 */
public class GreetingDynamicMethodPointcut extends DynamicMethodMatcherPointcut {


    /**
     * 对 Method 进行 动态切点 检查
     * @return
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        System.out.println("GreetingDynamicMethodPointcut method is :" + method);
        boolean matchFlag = "greetTo".equals(method.getName());
        return matchFlag;
    }

    /**
     * 对 Class 进行 静态切点 检查
     * @return
     */
    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                // 表示只能是 Waiter.class 或者 其子类
                boolean assignFlag = Waiter.class.isAssignableFrom(clazz);
                return assignFlag;
            }
        };
    }

}
