package com.deepblue.inaction_100_source_algorithm_back.sort;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 *
 */
public class SortQuick {

    public static void main(String[] args) {

        int[] array = new int[] {12, 2, 33, 33, 44, 54, 22, 100, 66, 99, 100};

        array = sort(array, 0, array.length - 1);

        Arrays.stream(array).forEach(System.out::println);

    }

    public static int[] sort(int[] array, int start, int end) {
        int i = start;
        int j = end;
        int base = array[start];

        while (i < j) {
            while(array[i] < base && i < j) {
                i++;
            }

            while(array[j] > base && i < j) {
                j--;
            }

            System.out.println(JSON.toJSONString(array) + ", base = " + base + ", i = " + i + ", j = " + j);

            if(array[i] == array[j] && i < j) {
                i++;
            } else if(array[i] > array[j] && i < j){
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        if(i-1 > start) {
            array = sort(array, start, i-1);
        }

        if(j+1 < end) {
            array = sort(array, j+1, end);
        }

        return array;
    }
}
