package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_017;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int count = Integer.valueOf(scanner.nextLine().trim());
        int order = Integer.valueOf(scanner.nextLine().trim());

        List<String> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String line = scanner.nextLine();
            list.add(line);
        }

        list = execute(list, order);
        list.stream().forEach(System.out::println);
    }

    public static List<String> execute(List<String> list, int order) {

        List<String> collect = list.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                int score1 = Integer.valueOf(o1.split(" ")[1]);
                int score2 = Integer.valueOf(o2.split(" ")[1]);

                if (order == 0) {
                    return score2 - score1;
                } else {
                    return score1 - score2;
                }
            }
        }).collect(Collectors.toList());

        return collect;
    }
}
