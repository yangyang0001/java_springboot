package com.deepblue.inaction_101_collection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 *
 */
public class TestConcurrentHashMap {

    public static void main(String[] args) {

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);


        Stream.of(map).forEach(System.out::println);


    }
}
