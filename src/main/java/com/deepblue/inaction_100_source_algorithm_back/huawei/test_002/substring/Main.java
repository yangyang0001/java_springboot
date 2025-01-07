package com.deepblue.inaction_100_source_algorithm_back.huawei.test_002.substring;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {

            String string = scanner.nextLine();
            Integer count = Integer.parseInt(scanner.nextLine().trim());

            System.out.println(string.substring(0, count));
        }

    }
}
