package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_004;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {

            String line = scanner.nextLine();
            System.out.println(num(line));
        }

    }

    public static int num(String line) {
        String[] params = line.trim().split("");

        Set<String> set = new HashSet<>();
        for (String s : params) {
            set.add(s);
        }

        return set.size();
    }
}
