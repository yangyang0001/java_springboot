package com.deepblue.inaction_00_spring.chapter_07_mine.example_004_aspectj_function_execution;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 *
 */
@Aspect
public class ExecutionAspect {

    /**
     * 从 execution 表达式中可以看出来规则使用非常难受, 且不容易用对, 推荐使用注解类的方式, 直接在方法上添加注解, 统一对注解规划使用!
     * @param point
     * @return
     */
    @Around("execution(* com.deepblue.inaction_00_spring.chapter_07_mine.example_004_aspectj_function_execution.MineObject.greet2(..))")
    public Object around(ProceedingJoinPoint point) {
        System.out.println("point args is :" + JSON.toJSONString(point.getArgs()));
        System.out.println("point kind is :" + JSON.toJSONString(point.getKind()));
        System.out.println("point signature is :" + JSON.toJSONString(point.getSignature()));
        System.out.println("point sourceLocation is :" + point.getSourceLocation());
        System.out.println("point staticPart is :" + point.getStaticPart());

        Object[] args = point.getArgs();
        Object result = null;
        try {
            result = point.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("result is :" + result);

        return result;
    }
}
