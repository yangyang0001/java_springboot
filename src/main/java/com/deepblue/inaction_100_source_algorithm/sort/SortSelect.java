package com.deepblue.inaction_100_source_algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * 选择排序, 图形参考地址: https://www.cs.usfca.edu/~galles/visualization/Algorithms.html
 */
public class SortSelect {

    public static void main(String[] args) {

        int[] arr = {1, 19, 12, 20, 44, 33};

        arr = sort(arr);

        System.out.println(JSON.toJSONString(arr));

    }

    public static int[] sort(int[] arr) {

        for(int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if(arr[j] < arr[i]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

        return arr;
    }
}
