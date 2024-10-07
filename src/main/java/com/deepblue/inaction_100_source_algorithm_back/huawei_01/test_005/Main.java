package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_005;

/**
 *
 */
public class Main {


    public static void main(String[] args) {
        int target = 4;
        int num = jump(target);
        System.out.println("num = " + num);
    }


    public static int jump(int target) {
        if(target == 1) {
            return 1;
        }

        if(target == 2) {
            return 2;
        }

        return jump(target - 1) + jump(target - 2);
    }
}
