package com.deepblue.inaction_00_spring.chapter_06_mine.example_001_methodbefore_advice;

import com.alibaba.fastjson.JSON;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 *
 */
public class MineBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("method is :" + method);
        System.out.println("args   is :" + JSON.toJSONString(args));
        System.out.println("target is :" + target);
        System.out.println("args[0] is:" + args[0]);
    }
}
