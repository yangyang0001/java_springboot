package com.deepblue.inaction_00_spring.chapter_09.example_002;

/**
 *
 */
public class SequeueNumber {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public Integer getNext() {
        Integer next = threadLocal.get();
        if(next == null) {
            next = 1;
        } else {
            next = next + 1;
        }

        threadLocal.set(next);
        return threadLocal.get();
    }
}
