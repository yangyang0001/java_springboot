package com.deepblue.inaction_00_spring.chapter_07.example_001_annotation;

import java.lang.reflect.Method;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Class clazz = MineService.class;

        Method[] methods = clazz.getDeclaredMethods();

        for(Method method : methods) {
            Mine annotation = method.getAnnotation(Mine.class);
            System.out.println(method.getName() + ", annotation value is :" + annotation.value());
        }

    }
}
