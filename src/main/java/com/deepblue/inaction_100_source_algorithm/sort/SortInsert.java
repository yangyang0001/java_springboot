package com.deepblue.inaction_100_source_algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * 插入排序, 图形参考地址: https://www.cs.usfca.edu/~galles/visualization/Algorithms.html
 */
public class SortInsert {

    public static void main(String[] args) {

        int[] arr = {1, 19, 12, 20, 44, 33};

        arr = sort(arr);

        System.out.println(JSON.toJSONString(arr));

    }

    public static int[] sort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                System.out.println("i = " + i);
                if(arr[j] < arr[j-1]) {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                } else {
                    break;
                }
            }
            System.out.println("--------------------");
        }

        return arr;
    }
}
