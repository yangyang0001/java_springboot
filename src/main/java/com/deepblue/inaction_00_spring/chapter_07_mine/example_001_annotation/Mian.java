package com.deepblue.inaction_00_spring.chapter_07_mine.example_001_annotation;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 */
public class Mian {

    public static void main(String[] args) throws IllegalAccessException {

        MineService service = new MineService();
        Class clazz = MineService.class;

        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();

        for (Field field : fields) {
            System.out.println("field name is :" + field.getName());
            System.out.println("field type is :" + field.getGenericType());
            MineFieldAnno annotation = field.getAnnotation(MineFieldAnno.class);
            System.out.println("field vale is :" + annotation.value());
            System.out.println("------------------------------------------------------------------");
        }

        System.out.println("\n\n");
        for (Method method : methods) {
            MineMethodAnno annotation = method.getAnnotation(MineMethodAnno.class);
            if (annotation != null) {
                System.out.println(method.getName() + ", annotation value is :" + annotation.value());
                System.out.println("------------------------------------------------------------------");
            }
        }



    }
}
