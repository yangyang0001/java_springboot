package com.deepblue.inaction_100_source_algorithm_back.huawei.test_003.stringsort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();

        Scanner scanner = new Scanner(System.in);

        int count = Integer.valueOf(scanner.nextLine());

        for(int i = 0; i < count; i++) {
            String line = scanner.nextLine();
            list.add(line);
        }

        list.sort((o1, o2) -> o1.compareTo(o2));

        list.stream().forEach(System.out::println);
    }
}
