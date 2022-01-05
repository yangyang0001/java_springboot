package com.deepblue.inaction_00_spring.chapter_06.example_003_method_interceptor;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 *
 */
public class GreetingMethodInterceptor implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Object[] arguments = invocation.getArguments();
        Method method = invocation.getMethod();

        System.out.println("arguments is : " + arguments);
        System.out.println("method    is : " + method);
        System.out.println(method.getName() + " before running");

        Object result = invocation.proceed();

        System.out.println(method.getName() + " after  running");

        return result;
    }

}
