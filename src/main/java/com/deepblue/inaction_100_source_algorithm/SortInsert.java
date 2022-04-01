package com.deepblue.inaction_100_source_algorithm;

import java.util.ArrayList;

/**
 * 插入排序, 图形参考地址: https://www.cs.usfca.edu/~galles/visualization/Algorithms.html
 *
 * 算法核心: 排完 前2个, 前3个, ... 到前n个!
 */
public class SortInsert {

    public static void main(String[] args) {
        int count = 0;

        ArrayList<Integer> list = new ArrayList<>();
        list.add(15);
        list.add(10);
        list.add(10);
        list.add(9);
        list.add(100);
        list.add(88);

        for(int i = 1; i < list.size(); i++) {
            count ++;
            for(int j = i; j >=1; j--) {
                count ++;
                int m = list.get(j);
                int n = list.get(j-1);
                if(m < n) {
                    int temp = m;
                    list.set(j, n);
                    list.set(j-1, temp);
                } else {
                    break;
                }
            }
        }

        System.out.println("count is : " + count);
        list.stream().forEach(System.out::println);

    }
}
