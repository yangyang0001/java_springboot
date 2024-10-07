package com.deepblue.inaction_00_spring.chapter_06_mine.example_004_exceptionadvice;

/**
 *
 */
public class MineObject implements MineInterface{
    @Override
    public void greet2() {
        int i = 0;
        int num = 10 / i;
    }

    @Override
    public void serve2() {
        String base = null;
        base.equals("zhangsan");
        System.out.println("serving to " + base);
    }
}
