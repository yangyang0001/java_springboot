package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_001;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() == 0) {
                break;
            }

            int num = getNum(line);
            System.out.println(num);
        }
    }

    public static int getNum(String line) {
        return Integer.parseInt(line.substring(2, line.length()), 16);
    }

}

