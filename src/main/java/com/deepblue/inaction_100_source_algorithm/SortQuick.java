package com.deepblue.inaction_100_source_algorithm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 */
public class SortQuick {

    public static int[] qsort(int arr[], int start, int end) {
        int base = arr[start];
        int i = start;
        int j = end;

        while (i<j) {

            while ((i<j) && (arr[j] > base)) {
                j--;
            }

            while ((i<j) && (arr[i] < base)) {
                i++;
            }

            if ((i<j) && (arr[i] == arr[j])) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        if (i-1 > start) {
            arr = qsort(arr, start,i-1);
        }

        if (j+1 < end) {
            arr = qsort(arr,j+1, end);
        }

        return (arr);
    }

    public static void main(String[] args) {
        int arr[] = new int[]{15, 10, 10, 9, 100, 88};
        int len = arr.length - 1;
        arr = qsort(arr,0, len);

        Arrays.stream(arr).forEach(System.out::println);
    }
}
