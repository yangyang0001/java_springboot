package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_020;

import com.alibaba.fastjson.JSON;
import org.omg.CORBA.StringValueHelper;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split(" ");
            List<List<Integer>> combine = combine(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
            System.out.println(combine);
        }
    }

    public static List<List<Integer>> combine(int n, int k) {
        Set<String> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set = execute(n, i, k, set);
        }
//        System.out.println("combine method invoke, set = " + JSON.toJSONString(set));

        List<String[]> collect = set.stream().map(item -> item.split("")).collect(Collectors.toList());
        List<List<Integer>> result = collect.stream().map(arr -> {
            return Arrays.stream(arr).map(item -> Integer.valueOf(item)).collect(Collectors.toList());
        }).collect(Collectors.toList());
        return result;
    }

    public static Set<String> execute(int n, int index, int k, Set<String> params) {

//        System.out.println("n = " + n + ", index = " + index + ", params = " + JSON.toJSONString(params));
        Set<String> mine = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            if(index == 1) {
                mine.add(String.valueOf(i));
            } else {
                boolean flag = false;
                for(String sub : params) {
                    if(sub.length() == k) {
                        flag = true;
                        break;
                    }
                    if(!sub.contains(String.valueOf(i))) {
                        String temp = sub + "" + i;
                        char[] array = temp.toCharArray();
                        Arrays.sort(array);
                        temp = new String(array);
//                        System.out.println("temp = " + temp);

                        if(!mine.contains(temp)) {
                            mine.add(new String(array));
                        }
                    }
                }

                if(flag) {
                    return params;
                }
            }
        }

        return mine;
    }
}
