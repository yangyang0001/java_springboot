package com.deepblue.inaction_00_spring.chapter_07.example_003_aspectj_function_annotation;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 *
 */
@Aspect
public class AnnotationAspect {

    @AfterReturning("@annotation(com.deepblue.inaction_00_spring.chapter_07.example_001_annotation.Mine)")
    public void needMine() {
        System.out.println("need annotation method invoke!");
    }

}
