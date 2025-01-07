package com.deepblue.inaction_00_spring.chapter_06.example_004_throws_exception_advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.sql.SQLException;

/**
 * 异常抛出增强中, ThrowsAdvice 没有提供任何的方法, 这里不需要按照下面的格式定义 增强的方法, 可参考 ThrowsAdvice
 * <pre class="code">public void afterThrowing(Exception ex)</pre>
 * <pre class="code">public void afterThrowing(RemoteException)</pre>
 * <pre class="code">public void afterThrowing(Method method, Object[] args, Object target, Exception ex)</pre>
 * <pre class="code">public void afterThrowing(Method method, Object[] args, Object target, ServletException ex)</pre>
 */
public class GreetingExceptionAdvice implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        System.out.println("A after throwing method    is : " + method);
        System.out.println("A after throwing args      is : " + args);
        System.out.println("A after throwing target    is : " + target);
        System.out.println("A after throwing ex        is : " + ex);
    }

    public void afterThrowing(SQLException exception) {
        System.out.println("B after throwing exception is : " + exception);
    }

    public void afterThrowing(RuntimeException exception) {
        System.out.println("C after throwing exception is : " + exception);
    }
}
