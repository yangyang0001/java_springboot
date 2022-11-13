package com.deepblue.inaction_100_source_algorithm_back.huawei.test_100_ceshi_01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String result = execute(line);
            System.out.println(result);
        }

    }

    public static String execute(String line) {

        List<Integer> collect = Arrays.stream(line.split(" ")).map(item -> Integer.valueOf(item)).collect(Collectors.toList());

        // 获取喊 过 的次数 之和
        int sum = 0;
        for(Integer s : collect) {
            if(s != 0) {
                sum += s;
            }
        }

        int[] result = new int[collect.size()];
        int count = 0;
        for (int i = 1; i < Integer.MAX_VALUE; i++) {

            if(i % 7 == 0 || String.valueOf(i).contains("7")) {
                int index = (i % collect.size() + collect.size() - 1) % collect.size();
                result[index] = result[index] + 1;
                count ++;
            }

            if(count  == sum) {
                break;
            }
        }

        return Arrays.stream(result).mapToObj(item -> String.valueOf(item)).collect(Collectors.joining(" ")).toString();
    }

}
