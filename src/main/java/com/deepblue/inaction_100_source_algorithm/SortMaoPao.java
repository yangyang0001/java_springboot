package com.deepblue.inaction_100_source_algorithm;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/**
 * 冒泡排序, 图形参考地址: https://www.cs.usfca.edu/~galles/visualization/Algorithms.html
 */
public class SortMaoPao {

    public static void main(String[] args) {

        int[] arr = {19, 2, 33, 4, 5, 100};

        int[] sort = sort(arr);
        System.out.println(JSON.toJSONString(sort));

    }

    public static int[] sort(int[] arr) {

        for(int i = 1; i < arr.length; i++) {
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
