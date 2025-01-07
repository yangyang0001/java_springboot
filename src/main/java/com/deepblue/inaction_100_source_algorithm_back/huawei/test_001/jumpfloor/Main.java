package com.deepblue.inaction_100_source_algorithm_back.huawei.test_001.jumpfloor;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextInt()) {
            int number = scanner.nextInt();
//            int count = jumpFloor(number);
            int count = jumpFloor2(number);
            System.out.println(count);
        }

    }

    public static int jumpFloor(int number) {

        if(number <= 1) {
            return 1;
        } else {
            return jumpFloor(number - 1) + jumpFloor(number - 2);
        }

    }

    public static int jumpFloor2(int number) {
        int a = 1;
        int b = 1;
        int c = 1;

        for(int i = 2; i <= number; i++) {
            c = a + b;
            a = b;
            b = c;
        }

        return c;
    }


}
