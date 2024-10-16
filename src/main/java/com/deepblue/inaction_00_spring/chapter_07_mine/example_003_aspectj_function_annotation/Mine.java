package com.deepblue.inaction_00_spring.chapter_07_mine.example_003_aspectj_function_annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Mine {

    boolean value() default true;

}
