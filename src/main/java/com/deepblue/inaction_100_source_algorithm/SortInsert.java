package com.deepblue.inaction_100_source_algorithm;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/**
 * 插入排序, 图形参考地址: https://www.cs.usfca.edu/~galles/visualization/Algorithms.html
 *
 * 算法核心: 排完 前2个, 前3个, ... 到前n个!
 */
public class SortInsert {

    public static void main(String[] args) {
        int[] arr = {19, 2, 33, 4, 5, 100};

        int[] sort = sort(arr);
        System.out.println(JSON.toJSONString(sort));

    }

    public static int[] sort(int[] arr) {

        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = i+1; j > 0; j--) {
                if(arr[j] < arr[j-1]) {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                } else {
                    break;
                }
            }
        }


        return arr;
    }
}
