package com.deepblue.inaction_00_spring.chapter_09_mine.example_002_sequeuenumber_illegal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

/**
 *
 */
@Data
@AllArgsConstructor
public class MineThread extends Thread {

    private MineSequeue sequeue;

    private CountDownLatch latch;

    @SneakyThrows
    @Override
    public void run() {
        latch.await();
        System.out.println("thread id is :" + Thread.currentThread().getId() + ", sequeue is :" + this.getSequeue().sequeue());
    }

}
