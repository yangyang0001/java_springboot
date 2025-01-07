package com.deepblue.inaction_101_collection;

import java.util.HashMap;
import java.util.stream.Stream;

/**
 *
 */
public abstract class TestHashMap {

    static int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {

        HashMap<String, Integer> map = new HashMap<>();

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put(null, 4);

        Stream.of(map).forEach(System.out::println);

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
