package com.deepblue.inaction_101_collection.example_002;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
public class StreamTest001 {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 3, 3, 5, 7, 9, 11);

        // 条件过滤
        List<Integer> collect0 = list.stream().filter(item -> item.intValue() > 2 && item.intValue() < 8).collect(Collectors.toList());
        System.out.println(collect0);

        // 转换成 set 集合
        Set<Integer> collect1 = list.stream().collect(Collectors.toSet());
        System.out.println(collect1);

        // 跳过前2个元素, 每个元素*2 然后求和
        Long count = list.stream().map(item -> item * 2).skip(2).collect(Collectors.summingLong(item -> item));
        System.out.println(count);

        List<Integer> firstList = Arrays.asList(1, 4, 6, 8, 44, 66, 8, 22);
        List<Integer> otherList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        // 找到两个集合中的相同的元素
        List<Integer> collect2 = firstList.stream().filter(item -> otherList.contains(item)).collect(Collectors.toList());
        System.out.println(collect2);

        List<String> strings = Arrays.asList("aa", "hello1", "world", "helloworld");
        // 查找结合中长度为5的元素
        List<String> collect3 = strings.stream().filter(item -> item.length() == 5).collect(Collectors.toList());
        System.out.println(collect3);

        // 找到以下集合中的所有的单词并且去重
        List<String> stringList = Arrays.asList("hello welcome", "hello world", "hello welcome", "welcome to jdk8");
        Set<String> collect = stringList.stream()
                .map(item -> item.split(" ")).collect(Collectors.toList()).stream()
                .flatMap(item -> Arrays.stream(item)).collect(Collectors.toSet());
        System.out.println(collect);

        List<String> list1 = Arrays.asList("Hi", "Hello", "你好");
        List<String> list2 = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");
        // 将 list1中 的每个单词 和 list2中 的每个单词进行拼接!
        list1.stream().flatMap(item1 -> list2.stream().map(item2 -> item1 + " " + item2)).forEach(System.out::println);

    }
}
