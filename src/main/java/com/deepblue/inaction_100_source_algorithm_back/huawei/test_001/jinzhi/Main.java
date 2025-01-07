package com.deepblue.inaction_100_source_algorithm_back.huawei.test_001.jinzhi;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {

            String line = scanner.nextLine();
            String trim = line.substring(2, line.length()).trim();

            int result = Integer.parseInt(trim, 16);
            System.out.println(result);

        }

    }
}
