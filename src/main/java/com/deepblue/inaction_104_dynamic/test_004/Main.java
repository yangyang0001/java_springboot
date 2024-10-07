package com.deepblue.inaction_104_dynamic.test_004;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
public class Main {


    public static void main(String[] args) {

//        int[] params = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] params = new int[]{4, 2, 0, 3, 2, 5};

        int result = trap(params);
        System.out.println(JSON.toJSONString(result));

    }

    public static int trap(int[] height) {

        if (height == null || height.length < 3) {
            return 0;
        }

        int n = height.length;
        int[] left = new int[n];
        left[0] = height[0];

        int[] right = new int[n];
        right[n - 1] = height[n - 1];

        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }

        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }


        int res = 0;
        for (int i = 0; i < n; i++) {
           res += Math.min(left[i], right[i]) - height[i];
        }

        return res;
    }

}
