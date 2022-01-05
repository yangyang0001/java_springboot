package com.deepblue.inaction_00_spring.chapter_07.example_001_annotation;

/**
 *
 */
public class MineService {

    @Mine(value = true)
    public void select(long mineId) {
        System.out.println("select mineId :" + mineId);
    }

    @Mine(value = false)
    public void delete(long mineId) {
        System.out.println("delete mineId :" + mineId);
    }
}
