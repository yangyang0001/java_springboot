package com.deepblue.inaction_103_mine.test_004;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
 */
public class Main {

    public static void main(String[] args) {
        int[] params = new int[]{2, 2, 3, 4, 5};
//        int[] params = new int[]{4, 2, 3, 4};
        int number = number(params);
        System.out.println(number);
    }

    public static int number(int[] params) {

        // 1、判空条件
        if (params == null || params.length < 3) {
            return 0;
        }

        int n = params.length;
        // 2、获取 三条边
        List<String> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(String.valueOf(i));
        }
        for (int i = 1; i < 3; i++) {
            result = execute(n, result);
        }
        result.stream().forEach(System.out::println);

        return compute(result, params);
    }

    public static List<String> execute(int n, List<String> list) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String exist = list.get(i);
            for (int j = exist.split(" ").length; j < n; j++) {
                if(!exist.contains(String.valueOf(j))) {
                    String mine = exist + " " + j;
                    String[] split = mine.split(" ");
                    List<Integer> collect = Arrays.stream(split).mapToInt(item -> Integer.valueOf(item)).boxed()
                            .collect(Collectors.toList());
                    Collections.sort(collect);
                    String minestring = collect.stream().map(item -> String.valueOf(item)).collect(Collectors.joining(" "));
                    if(!result.contains(minestring)) {
                        result.add(minestring);
                    }
                }
            }
        }
        return result;
    }

    public static int compute(List<String> list, int[] params) {

        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            String[] split = s.split(" ");
            int a = Integer.valueOf(split[0]);
            int b = Integer.valueOf(split[1]);
            int c = Integer.valueOf(split[2]);
            if (params[a] + params[b] > params[c] && params[a] + params[c] > params[b] && params[b] + params[c] > params[a]) {
                sum++;
            }
        }

        return sum;
    }


}
