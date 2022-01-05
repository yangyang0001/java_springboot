package com.deepblue.inaction_00_spring.chapter_07.example_008_aspectj_joinpoint_info;

import com.alibaba.fastjson.JSON;
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

    @Around("MinePointcut.getBook()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("mine aspect around method invoke start!");

        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        Object target = joinPoint.getTarget();
        System.out.println("around args      is :" + JSON.toJSONString(args));
        System.out.println("around signature is :" + JSON.toJSONString(signature));
        System.out.println("around target    is :" + JSON.toJSONString(target));

        Object proceed = joinPoint.proceed();

        System.out.println("around proceed    is :" + JSON.toJSONString(proceed));

        System.out.println("mine aspect around method invoke end!");

    }
}
