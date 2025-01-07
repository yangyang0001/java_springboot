package com.deepblue.inaction_00_spring.chapter_06.example_001_method_before_advice;

import com.alibaba.fastjson.JSON;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 *
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("method is : " + method);
        System.out.println("args   is : " + JSON.toJSONString(args));
        System.out.println("target is : " + target);

        System.out.println("How are you " + args[0]);
    }
}
