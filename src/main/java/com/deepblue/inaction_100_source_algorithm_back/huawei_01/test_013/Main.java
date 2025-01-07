package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_013;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        Integer count = Integer.valueOf(scanner.nextLine());

        String substring = line.substring(0, count);
        System.out.println(substring);
    }

}
