package com.deepblue.inaction_100_source_algorithm_back.huawei.test_100_ceshi_02;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int count = Integer.valueOf(scanner.nextLine());
        String param = scanner.nextLine();
        int boxed = Integer.parseInt(scanner.nextLine());

        int maxsum = maxsum(count, param, boxed);
        System.out.println(maxsum);

    }

    public static int maxsum(int count, String line2, int boxed) {

        int result = Integer.MIN_VALUE;
        String[] param = line2.split(" ");

        for (int i = 0; i < param.length - boxed + 1; i++) {
            int sum = 0;
            for (int j  = i; j < i + boxed; j++) {
                sum += Integer.valueOf(param[j]);
            }

            if(sum > result) {
                result = sum;
            }
        }

        return result;
    }
}
