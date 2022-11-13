package com.deepblue.inaction_100_source_algorithm_back.sort;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 *
 */
public class SortSelect {

    public static void main(String[] args) {

        int[] array = new int[] {12, 2, 44, 54, 22, 100, 66, 99};

        array = sort(array);

        Arrays.stream(array).forEach(System.out::println);

    }

    public static int[] sort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i+1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }

        return array;
    }
}
