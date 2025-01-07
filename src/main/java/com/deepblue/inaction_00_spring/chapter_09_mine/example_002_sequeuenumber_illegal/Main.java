package com.deepblue.inaction_00_spring.chapter_09_mine.example_002_sequeuenumber_illegal;

import java.util.concurrent.CountDownLatch;

/**
 * 当前 MineSequeue 使用ThreadLocal 并不能起到排他的作用!
 */
public class Main {


    public static void main(String[] args) {

        MineSequeue sequeue = new MineSequeue();

        CountDownLatch cdl = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            new MineThread(sequeue, cdl).start();
            System.out.println("count down latch, count = " + cdl.getCount());
            cdl.countDown();
        }

    }


}
