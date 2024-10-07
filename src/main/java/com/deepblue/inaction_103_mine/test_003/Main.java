package com.deepblue.inaction_103_mine.test_003;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 题目描述
 * 给定两个整数n、m ，输出从(1,2,…,n)中选出m个数的所有组合。
 *
 * 输入
 * 每个测试文件含有多个数据，输入两个整数n,m(0<m≤n≤10)。输入到文件末尾结束。
 *
 * 输出
 * 输出对应的组合数，每个组合中的数字由小到大排列，对于每组数据的所有组合按字典序排序。
 *
 * 样例
 * 输入样例 1 复制
 * 3 2
 * 3 1
 *
 * 输出样例 1
 * 1 2
 * 1 3
 * 2 3
 * 1
 * 2
 * 3
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split(" ");
            int n = Integer.valueOf(split[0]);
            int m = Integer.valueOf(split[1]);
            List<String> result = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                result.add(String.valueOf(i));
            }
            for (int i = 1; i < m; i++) {
                result = execute(n, m, result);
            }
            result.stream().forEach(System.out::println);
        }
    }
    public static List<String> execute(int n, int m, List<String> list) {
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


}
