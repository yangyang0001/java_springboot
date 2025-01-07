package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_002;

import com.alibaba.fastjson.JSON;

/**
 *
 */
public class Main {


    public static void main(String[] args) {

        int[] nums = {3, 2, 3};
        int target = 6;

        int[] result = getIndex(nums, target);

        System.out.println(JSON.toJSONString(result));

    }

    public static int[] getIndex(int[] nums, int target) {

        int[] result = null;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    result = new int[2];
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return result;
    }
}
