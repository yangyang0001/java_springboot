package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_040;

import com.alibaba.fastjson.JSON;

import java.util.Scanner;

/**
 *
 */
public class Main {


    public static void main(String[] args) {

//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextLine()) {
//            String line1 = scanner.nextLine();
//            String line2 = scanner.nextLine();
//
//        }


        int[] nums = {1, 3, 5, 100, 10, 99, 88, -1};
        execute(nums);


    }

    public static void execute(String line1, String line2) {

    }

    public static void execute(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i; j++) {
                if(nums[j] > nums[j+1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }

        System.out.println(JSON.toJSONString(nums));

    }
}
