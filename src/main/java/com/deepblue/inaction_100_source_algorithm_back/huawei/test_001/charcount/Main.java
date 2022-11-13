package com.deepblue.inaction_100_source_algorithm_back.huawei.test_001.charcount;

import java.util.Arrays;
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
            Set<String> charSet = new HashSet<String>();
            String line = scanner.nextLine();
            charSet.addAll(Arrays.asList(line.split("")));
            System.out.println(charSet.size());
        }

    }
}
