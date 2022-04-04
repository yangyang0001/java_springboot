package com.deepblue.inaction_100_source_algorithm;

import com.alibaba.fastjson.JSON;

/**
 *
 */
public class SortSelection_Array {

    public static void main(String[] args) {

        int[] arr = {10, 18, 8, 4, 22, 5};

        for(int i = 0; i < arr.length; i++) {
            int base = arr[i];
            for(int j = i+1; j < arr.length; j++) {
                if(base > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println(JSON.toJSONString(arr));



    }
}
