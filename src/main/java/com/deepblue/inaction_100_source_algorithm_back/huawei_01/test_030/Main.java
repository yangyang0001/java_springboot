package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_030;

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
            int num = Integer.valueOf(scanner.nextLine());
            int cou = countPrimes(num);
            System.out.println(cou);
        }
    }

    public static int countPrimes(int num) {
        if(num <= 1) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i < num; i++) {
            if(isNumSu(i)) {
                set.add(i);
            }
        }
        return set.size();
    }

    public static boolean isNumSu(int num) {
        if(num <= 1) {
            return false;
        }

        boolean flag = true;

        int half = num / 2;
        for (int i = 2; i <= half; i++) {
            if(num % i == 0) {
                flag = false;
            }
        }

        return flag;
    }
}
