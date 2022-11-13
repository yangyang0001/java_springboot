package com.deepblue.inaction_100_source_algorithm_back.huawei.test_001.random;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < count; i++) {
            int random = scanner.nextInt();
            set.add(random);
        }

        List<Integer> collect = set.stream().collect(Collectors.toList());
        collect.sort(Comparator.comparingInt(Integer::intValue));
        collect.stream().forEach(System.out::println);

    }

}
