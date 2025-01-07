package com.deepblue.inaction_00_spring.chapter_09.example_002;

/**
 *
 */
public class Client {

    public static void main(String[] args) {

        SequeueNumber sequeueNumber = new SequeueNumber();

        MineThread thread1 = new MineThread(sequeueNumber);
        MineThread thread2 = new MineThread(sequeueNumber);
        MineThread thread3 = new MineThread(sequeueNumber);

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
