package com.deepblue.inaction_100_source_algorithm;

import java.util.ArrayList;

/**
 * 选择排序, 图形参考地址: https://www.cs.usfca.edu/~galles/visualization/Algorithms.html
 */
public class SortSelection {

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(15);
        list.add(10);
        list.add(10);
        list.add(9);
        list.add(100);
        list.add(88);

        for(int i = 0; i < list.size(); i++) {
            int minIndex = i;
            for(int j = i+1; j < list.size(); j++) {
                int m = list.get(minIndex);
                int n = list.get(j);
                if(m > n) {
                    minIndex = j;
                }
            }

            int temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }

        list.stream().forEach(System.out::println);

    }
}
