package com.deepblue.inaction_00_spring.chapter_07_mine.example_001_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface MineFieldAnno {

    String value() default "1";

}
