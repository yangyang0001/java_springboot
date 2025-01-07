package com.deepblue.inaction_00_spring.chapter_06_mine.example_002_methodreturn_advice;

import com.alibaba.fastjson.JSON;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 *
 */
public class MineReturnAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("returnValue is : " + returnValue);
        System.out.println("method      is : " + method);
        System.out.println("args        is : " + JSON.toJSONString(args));
        System.out.println("target      is : " + target);
    }
}
