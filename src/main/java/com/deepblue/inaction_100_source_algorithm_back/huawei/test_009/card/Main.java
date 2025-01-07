package com.deepblue.inaction_100_source_algorithm_back.huawei.test_009.card;

import java.util.*;
import java.util.stream.Collectors;

/**
 * {6, 0, 2, 0, 4};
 * {0, 3, 2, 6, 4};
 * {1, 0, 0, 1, 0};
 * {13, 12, 11, 0, 1};
 */
public class Main {

    public static void main(String[] args) {

        int[] numbers = {0, 1, 2, 3, 0};            // true
        boolean continuous = isContinuous(numbers);
        System.out.println(continuous);

    }

    public static boolean isContinuous(int[] numbers) {
        List<Integer> collect = Arrays.stream(numbers).boxed().collect(Collectors.toList());

        // 非零个数
        long count0 = collect.stream().filter(item -> item != 0).count();

        // 非零去重个数
        long count1 = collect.stream().filter(item -> item != 0).collect(Collectors.toSet()).stream().count();

        if(count0 != count1) {
            return false;
        }

        // 取出最大最小值
        int max = collect.stream().filter(item -> item != 0).max((o1, o2) -> o1 - o2).get();
        int min = collect.stream().filter(item -> item != 0).min((o1, o2) -> o1 - o2).get();

        if(max - min > 4) {
            return false;
        }

        System.out.println("count0 is:" + count0);
        System.out.println("count1 is:" + count1);
        System.out.println("max    is:" + max);
        System.out.println("min    is:" + min);

        return true;
    }

}
