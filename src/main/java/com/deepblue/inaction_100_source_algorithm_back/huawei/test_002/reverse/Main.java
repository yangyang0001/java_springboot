package com.deepblue.inaction_100_source_algorithm_back.huawei.test_002.reverse;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String reve = reverse(line);
            System.out.println(reve);
        }

    }

    public static String reverse(String line) {

        StringBuilder builder = new StringBuilder(line);
        StringBuilder reverse = builder.reverse();

        return reverse.toString();
    }
}
