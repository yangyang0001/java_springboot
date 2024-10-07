package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_003;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int[] arr = new int[501];

        for (int i = 0; i < count; i++) {
            int num = scanner.nextInt();
            arr[num] = num;
        }

        Arrays.stream(arr).filter(item -> item > 0).forEach(System.out::println);
    }
}
