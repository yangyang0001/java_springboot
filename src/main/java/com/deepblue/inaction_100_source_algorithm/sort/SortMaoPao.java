package com.deepblue.inaction_100_source_algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * 冒泡排序, 图形参考地址: https://www.cs.usfca.edu/~galles/visualization/Algorithms.html
 */
public class SortMaoPao {

    public static void main(String[] args) {

        int[] arr = {1, 19, 12, 20, 44, 33};

        arr = sort(arr);

        System.out.println(JSON.toJSONString(arr));

    }

    public static int[] sort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            for(int j = 0; j < arr.length - i; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        return arr;
    }
}
