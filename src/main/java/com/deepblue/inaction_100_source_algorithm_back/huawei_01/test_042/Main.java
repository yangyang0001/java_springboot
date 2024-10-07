package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_042;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String param = line.split(" ")[0];
            String regex = line.split(" ")[1];

            boolean flag = isMatch(param, regex);
            System.out.println(flag);
        }

    }

    public static boolean isMatch(String param, String regex) {
       return param.matches(regex);
    }
}
