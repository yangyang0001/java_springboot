package com.deepblue.inaction_100_source_algorithm;

import com.alibaba.fastjson.JSON;

/**
 *
 */
public class SortInsert_Array {

    public static void main(String[] args) {

        int[] arr = {10, 18, 8, 4, 22, 5};

        for(int i = 1; i < arr.length; i++) {
            for(int j = i; j > 0; j--) {
                if(arr[j] < arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                } else {
                    break;
                }
            }
        }

        System.out.println(JSON.toJSONString(arr));


    }
}
