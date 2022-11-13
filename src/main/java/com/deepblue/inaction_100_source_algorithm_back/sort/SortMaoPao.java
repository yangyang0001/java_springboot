package com.deepblue.inaction_100_source_algorithm_back.sort;

import java.util.Arrays;

/**
 *
 */
public class SortMaoPao {

    public static void main(String[] args) {
        int[] array = new int[] {12, 2, 44, 54, 22, 100, 66, 99};

        array = sort(array);

        Arrays.stream(array).forEach(System.out::println);
    }

    public static int[] sort(int[] array) {

        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if(array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }

        return array;
    }
}
