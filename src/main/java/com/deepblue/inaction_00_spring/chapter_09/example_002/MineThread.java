package com.deepblue.inaction_00_spring.chapter_09.example_002;

/**
 *
 */
public class MineThread extends Thread{

    private SequeueNumber sequeueNumber;

    public MineThread(SequeueNumber sequeueNumber) {
        this.sequeueNumber = sequeueNumber;
    }

    @Override
    public void run() {
        for(int i = 0; i < 3; i++) {
            System.out.println("thread id is " + Thread.currentThread().getId() + ", next is :" + sequeueNumber.getNext());
        }
    }
}
