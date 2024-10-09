package com.deepblue.inaction_00_spring.chapter_07_mine.example_005_pointcut_namespace;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 *
 */
@Aspect
public class MineAspect {

    @Around("MinePointcut.selectId()")
    public Object around(ProceedingJoinPoint point) {
        System.out.println("args is :" + JSON.toJSONString(point.getArgs()));
        System.out.println("kind is :" + JSON.toJSONString(point.getKind()));
        System.out.println("signature is :" + JSON.toJSONString(point.getSignature()));
        System.out.println("sourceLocation is :" + point.getSourceLocation());
        System.out.println("staticPart is :" + point.getStaticPart());
        System.out.println("before selectId method invoke!");

        Object[] args = point.getArgs();
        Object result = null;

        try {
            result = point.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("after  selectId method invoke!");

        return result;
    }

    @Before("MinePointcut.deleteId()")
    public void before(JoinPoint joinPoint) {
        System.out.println("before deleteId method invoke!");

        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        Object target = joinPoint.getTarget();
        System.out.println("before args      is :" + JSON.toJSONString(args));
        System.out.println("before signature is :" + JSON.toJSONString(signature));
        System.out.println("before target    is :" + JSON.toJSONString(target));

    }
}
