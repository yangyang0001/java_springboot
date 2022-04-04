package com.deepblue.inaction_100_source_algorithm;

import com.alibaba.fastjson.JSON;

/**
 *
 */
public class SortMaoPao_Array {

    public static void main(String[] args) {

        int[] arr = {10, 18, 8, 4, 22, 5};

        for(int i = 0; i < arr.length; i++) {
            for(int j = i+1; j < arr.length; j++) {
                int m = arr[i];
                int n = arr[j];
                if(m > n) {
                    int temp = m;
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println(JSON.toJSONString(arr));

    }

}
