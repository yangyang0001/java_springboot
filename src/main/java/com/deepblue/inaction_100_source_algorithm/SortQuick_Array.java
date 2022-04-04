package com.deepblue.inaction_100_source_algorithm;

import com.alibaba.fastjson.JSON;

/**
 *
 */
public class SortQuick_Array {

    public static void main(String[] args) {

        int[] arr = {10, 18, 8, 4, 22, 5};

        arr = qsort(arr, 0, arr.length -1);

        System.out.println(JSON.toJSONString(arr));

    }

    public static int[] qsort(int[] arr, int start, int end) {

        int base = arr[start];
        int i = start;
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

            if(i - 1 > start) {
                arr = qsort(arr, start, i - 1);
            }

            if(j + 1 < end) {
                arr = qsort(arr, j + 1, end);
            }

        }

        return arr;
    }
}
