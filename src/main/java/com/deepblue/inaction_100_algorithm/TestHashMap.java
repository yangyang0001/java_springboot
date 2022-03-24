package com.deepblue.inaction_100_algorithm;

import java.util.HashMap;

/**
 *
 */
public abstract class TestHashMap {

    static int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {

        HashMap<String, String> map = new HashMap<>();

        System.out.println(tableSizeFor(8));


    }

    static int tableSizeFor(int cap) {
        int n = cap - 1;
        System.out.println("n is " + n);
        n |= n >>> 1;
        System.out.println("n |= n >>> 1 after the n is :" + n);
        n |= n >>> 2;
        System.out.println("n |= n >>> 2 after the n is :" + n);
        n |= n >>> 4;
        System.out.println("n |= n >>> 4 after the n is :" + n);
        n |= n >>> 8;
        System.out.println("n |= n >>> 5 after the n is :" + n);
        n |= n >>> 16;
        System.out.println("n |= n >>> 16 after the n is :" + n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
