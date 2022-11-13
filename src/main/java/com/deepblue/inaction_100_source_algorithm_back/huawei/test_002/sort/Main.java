package com.deepblue.inaction_100_source_algorithm_back.huawei.test_002.sort;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String count = scanner.nextLine();
        String line  = scanner.nextLine();
        String order = scanner.nextLine();

        String sort = sort(Integer.valueOf(count), line, Integer.valueOf(order));
        System.out.println(sort);
    }


    public static String sort(int count, String line, int order) {

        List<Integer> collect = Arrays.stream(line.split(" ")).map(item -> Integer.valueOf(item)).collect(Collectors.toList());

        collect.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(order == 0) {
                    return o1 - o2;
                } else {
                    return o2 - o1;
                }
            }
        });

        String result = collect.stream().map(item -> String.valueOf(item)).collect(Collectors.joining(" ")).toString();

        return result;
    }
}
