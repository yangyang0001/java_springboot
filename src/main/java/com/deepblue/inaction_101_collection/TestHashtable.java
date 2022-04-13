package com.deepblue.inaction_101_collection;

import java.util.Hashtable;
import java.util.stream.Stream;

/**
 *
 */
public class TestHashtable {

    public static void main(String[] args) {

        Hashtable<String, Integer> numbers = new Hashtable<>();
        numbers.put("one", 1);
        numbers.put("two", 2);
        numbers.put("three", 3);

        // Hashtable 不允许存在 key = null 或者 value = null 的情况!
        numbers.put(null, 4);
        numbers.put("four", null);

        Stream.of(numbers).forEach(System.out::println);


    }
}
