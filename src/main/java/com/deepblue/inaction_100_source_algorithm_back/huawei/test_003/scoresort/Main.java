package com.deepblue.inaction_100_source_algorithm_back.huawei.test_003.scoresort;

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

        int count = Integer.valueOf(scanner.nextLine());
        int order = Integer.valueOf(scanner.nextLine());

        List<String> list = new ArrayList<String>();
        for(int i = 0; i < count; i++) {
            String line = scanner.nextLine();
            list.add(line);
        }

        List<String> scoresort = scoresort(list, order);
        scoresort.stream().forEach(System.out::println);
    }

    /**
     * 4
     * 1
     * fang 90
     * yang 50
     * lang 50
     * ning 70
     * @param list
     * @param order
     * @return
     */
    public static List<String> scoresort(List<String> list, int order) {

        List<String> collect = list.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int score1 = Integer.parseInt(o1.split(" ")[1]);
                int score2 = Integer.parseInt(o2.split(" ")[1]);
                if(order == 1) {
                    return score1 - score2;
                } else {
                    return score2 - score1;
                }
            }
        }).collect(Collectors.toList());

        return collect;
    };
}
