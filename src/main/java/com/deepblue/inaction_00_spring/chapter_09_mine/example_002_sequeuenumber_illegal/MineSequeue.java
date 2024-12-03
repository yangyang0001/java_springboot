package com.deepblue.inaction_00_spring.chapter_09_mine.example_002_sequeuenumber_illegal;

/**
 * 书中给出的例子, 当前序列并非有效序列, ThreadLocal 所致! 每一个线程在 ThreadLocal 每种类型的值只能是一个!
 */
public class MineSequeue {

    public ThreadLocal<Integer> mineLocal = new ThreadLocal();

    public Integer sequeue() {

        Integer num = mineLocal.get();

        if(num == null) {
            num = 1;
        } else {
            num = num + 1;
        }

        mineLocal.set(num);

        return mineLocal.get();
    }
}
