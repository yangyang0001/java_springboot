package com.deepblue.inaction_00_spring.chapter_07_mine.example_001_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface MineMethodAnno {
    boolean value() default true;
}
