package com.deepblue.inaction_100_source_algorithm;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/**
 * 选择排序, 图形参考地址: https://www.cs.usfca.edu/~galles/visualization/Algorithms.html
 */
public class SortSelection {

    public static void main(String[] args) {

        int[] arr = {19, 2, 33, 4, 5, 100};

        int[] sort = sort(arr);
        System.out.println(JSON.toJSONString(sort));

    }

    public static int[] sort(int[] arr) {

        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = i+1; j < arr.length - 1; j++) {
                if(arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        return arr;
    }
}
