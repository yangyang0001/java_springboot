package com.deepblue.inaction_100_source_algorithm.search;

import com.alibaba.fastjson.JSON;

/**
 * 二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {

        int[] arr = {1, 3, 5, 8, 10, 22, 100};

        int num = 100;

        int index = search(arr, 0, arr.length - 1, num);

        System.out.println("index      = " + index);
        System.out.println("arr[index] = " + arr[index]);

    }

    public static int search(int[] arr, int begin, int end, int num) {

        int middle = (begin + end) / 2;
        int index = middle;

        System.out.println("begin  = " + begin + ", end    = " + end + ", middle = " + middle);

        if(arr[middle] == num) {
            return middle;
        }

        if(begin == middle) {
            return -1;
        }

        if(arr[middle] > num) {
            index = search(arr, begin, middle, num);
        }

        if(arr[middle] < num) {
            index = search(arr, middle + 1, end, num);
        }

        return index;
    }
}
