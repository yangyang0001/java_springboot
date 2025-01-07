package com.deepblue.inaction_100_source_algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * 快速排序, 图形参考地址: https://www.cs.usfca.edu/~galles/visualization/Algorithms.html
 */
public class SortQuick {

    public static void main(String[] args) {

        int[] arr = {1, 19, 12, 20, 44, 33};

        arr = sort(arr, 0 , arr.length - 1);

        System.out.println(JSON.toJSONString(arr));

    }

    public static int[] sort(int[] arr, int start, int end) {

        int i = start;
        int j = end;
        int base = arr[i];

        while (i < j) {

            while (i < j & arr[i] < base) {
                i++;
            }

            while (i < j & arr[j] > base) {
                j--;
            }

            if (i < j && arr[i] == arr[j]) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }

        }

        if(i - 1 > start) {
            arr = sort(arr, start, i - 1);
        }

        if(j + 1 < end) {
            arr = sort(arr, j + 1, end);
        }

        return arr;
    }
}
