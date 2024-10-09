package com.deepblue.inaction_00_spring.chapter_07_mine.example_002_aspectj_base;


import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MineAspect {

    @Around("execution(* greet2(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        System.out.println("point args is :" + JSON.toJSONString(point.getArgs()));
        System.out.println("point kind is :" + JSON.toJSONString(point.getKind()));
        System.out.println("point signature is :" + JSON.toJSONString(point.getSignature()));
        System.out.println("point sourceLocation is :" + point.getSourceLocation());
        System.out.println("point staticPart is :" + point.getStaticPart());

        Object[] args = point.getArgs();
        Object result = point.proceed(args);

        System.out.println("result is :" + result);

        return result;
    }


}
