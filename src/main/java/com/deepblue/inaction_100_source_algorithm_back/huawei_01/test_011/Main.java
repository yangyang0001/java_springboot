package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_011;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String execute = execute(line);
            System.out.println(execute);
        }

    }

    public static String execute(String line) {
        String[] split = line.split("");
        StringBuffer buffer = new StringBuffer("");
        Arrays.stream(split).forEach(item -> buffer.append(item));
        return buffer.reverse().toString();
    }
}
