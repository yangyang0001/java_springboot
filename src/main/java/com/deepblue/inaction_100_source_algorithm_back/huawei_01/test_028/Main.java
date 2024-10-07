package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_028;

import java.util.*;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            Integer num = Integer.valueOf(scanner.nextLine());
            int[] ints = execute(num);
            Arrays.stream(ints).forEach(System.out::println);
        }

    }

    public static int[] execute(Integer num) {
        List<Integer> sus = new ArrayList<>();
        for (int i = 2; i < num; i++) {
            if(isNumSu(i)) {
                sus.add(i);
            }
        }

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < sus.size(); i++) {
            if(num == 2 * sus.get(i)) {
                return new int[] {sus.get(i), sus.get(i)};
            } else {
                int left = num - sus.get(i);
                if(sus.contains(left)) {
                    int[] exist = new int[] {sus.get(i), left};
                    list.add(exist);
                }
            }
        }

        return list.stream().sorted(new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                int sub1 = Math.abs(arr1[0] - arr1[1]);
                int sub2 = Math.abs(arr2[0] - arr2[1]);
                return sub1 - sub2;
            }
        }).findFirst().get();
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
