package com.deepblue.inaction_00_spring.chapter_07.example_003_aspectj_function_annotation;

import com.deepblue.inaction_00_spring.chapter_06.Waiter;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 * 切点函数: 注解 切点函数
 */
public class Main {

    public static void main(String[] args) {

        Waiter waiter =  new AnnotationWaiter();

        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(waiter);
        factory.addAspect(AnnotationAspect.class);

        Waiter proxy = factory.getProxy();
        proxy.greetTo("zhangsan");
        System.out.println("------------------------------------------------------------------------------------------------------");
        proxy.serveTo("lisi");

    }
}
