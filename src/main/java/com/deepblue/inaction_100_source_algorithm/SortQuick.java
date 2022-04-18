package com.deepblue.inaction_100_source_algorithm;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 */
public class SortQuick {

    public static void main(String[] args) {
        int[] arr = {19, 2, 33, 4, 5, 100, 3, 3, 7, 9, 122344, 4656, 34, 34, 4656, 5, 6};

        int[] sort = sort(arr,0, arr.length - 1);
        System.out.println(JSON.toJSONString(sort));
    }

    public static int[] sort(int[] arr, int begin, int end) {
        int base = arr[begin];

        int i = begin;
        int j = end;


        while(i < j) {

            while(i < j && arr[i] < base) {
                i++;
            }

            while(i < j && arr[j] > base) {
                j--;
            }

            if(i < j && arr[i] == arr[j]) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }

        }

        if(i - 1 > begin) {
            arr = sort(arr, begin, i - 1);
        }

        if(j + 1 < end) {
            arr = sort(arr, j + 1, end);
        }

        return arr;
    }
}
