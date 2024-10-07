package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_032;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class Main {

    public static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
//        int[] nums = new int[]{10, 0, 12, 13, 9};
//        int[] nums = new int[]{0, 12, 11, 11, 0};
        int[] nums = new int[]{11, 0, 9, 0, 0};
        System.out.println(isStraight(nums));
    }

    public static boolean isStraight(int[] nums) {

        List<Integer> source = Arrays.stream(nums).boxed().collect(Collectors.toList());
        List<Integer> zelist = source.stream().filter(item -> item == 0).collect(Collectors.toList());
        List<Integer> otlist = source.stream().filter(item -> item != 0).sorted().collect(Collectors.toList());

        if(zelist.size() == 5) {
            return true;
        }

        int size = otlist.stream().collect(Collectors.toSet()).size();
        if(size < otlist.size()) {
            return false;
        }

        int start = 0;
        int end = 0;
        if(otlist.get(0) <= 1) {
            start = 1;
            end = 6;
        } else if(otlist.get(0) >= 9) {
            start = 9;
            end = 14;
        } else {
            start = otlist.get(0);
            end = otlist.get(0) + 5;
        }

        List<Integer> stande = list.subList(start, end);
//        System.out.println("source = " + JSON.toJSONString(source));
//        System.out.println("zelist = " + JSON.toJSONString(zelist));
//        System.out.println("otlist = " + JSON.toJSONString(otlist));
//        System.out.println("stande = " + JSON.toJSONString(stande));

        stande = stande.stream().filter(item -> !otlist.contains(item)).collect(Collectors.toList());
        if (stande.size() == zelist.size()) {
            return true;
        } else {
            return false;
        }

    }

    static {
        for (int i = 0; i <= 13; i++) {
            list.add(i);
        }
    }
}
