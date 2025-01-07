package com.deepblue.inaction_01_springboot2.chapter_02.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
@Aspect
public class AopConfig {

    @Around("CommonPointcut.controller()")
    public Object simpleAround(ProceedingJoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();
            System.out.println("simple around invoke   args  is :" + JSON.toJSONString(args));

            Object proceed = joinPoint.proceed();

            System.out.println("simple around invoke proceed is :" + JSON.toJSONString(proceed));
            return proceed;

        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;
    }

}
