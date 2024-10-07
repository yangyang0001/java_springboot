package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_021;

import com.alibaba.fastjson.JSON;
import org.redisson.misc.Hash;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 3, 3, 70, 100, 10, 11, 12, 13};
//        int[] nums1 = {2, 2, 2, 2, 2};

        int lengthest = findLengthOfLCIS(nums1);
        System.out.println(lengthest);
    }

    public static int findLengthOfLCIS(int[] nums) {

        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int index = i;
                List<Integer> source = new ArrayList<Integer>();
                List<Integer> target = new ArrayList<Integer>();
                for (int k = 0; k <= j-i; k++) {
                    source.add(nums[index]);
                    target.add(nums[index]);
                    index++;
                }

                Collections.sort(target);
                Set<Integer> tarset = new HashSet<>();
                tarset.addAll(target);

                String s = source.stream().map(item -> String.valueOf(item)).collect(Collectors.joining());
                String t = target.stream().map(item -> String.valueOf(item)).collect(Collectors.joining());

//                System.out.println("s = " + s + ", max = " + max);
//                System.out.println("t = " + t + ", max = " + max);

                if(source.size() == tarset.size() && s.equals(t) && source.size() > max) {
                    max = source.size();
                }
            }
        }

        return max;
    }
}
