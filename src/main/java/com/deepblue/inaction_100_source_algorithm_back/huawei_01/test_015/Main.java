package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int count = Integer.valueOf(scanner.nextLine());

        List<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(scanner.nextLine().trim());
        }

        list.sort((o1, o2) -> o1.compareTo(o2));
        list.stream().forEach(System.out::println);
    }

}
