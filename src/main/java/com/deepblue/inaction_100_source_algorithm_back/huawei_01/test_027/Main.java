package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_027;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

//        int count = Integer.valueOf(scanner.nextLine());
//        List<Integer> params = Arrays.stream(scanner.nextLine().split(" "))
//                .mapToInt(item -> Integer.valueOf(item)).boxed().collect(Collectors.toList());
//        execute(count, params);

        while(scanner.hasNextLine()) {
            List<Integer> params = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(item -> Integer.valueOf(item)).boxed().collect(Collectors.toList());
            execute(params);
        }

    }

    /**
     * 2 5 6 13
     * 2 5 6 13 17 19
     * @param params
     * @return
     */
//    public static void execute(int count, List<Integer> params) {
    public static List<Integer> execute(List<Integer> params) {
        if(params.size() <= 2) {
            return params;
        }

        List<List<Integer>> mine = new ArrayList<>();
        for (int i = 0; i < params.size(); i++) {
            for (int j = i+1; j < params.size(); j++) {
                List<Integer> prefix = new ArrayList<>();
                prefix.add(params.get(i));
                prefix.add(params.get(j));

                mine.add(prefix);

                List<Integer> suffix = new ArrayList<>(params);
                suffix.remove(params.get(i));
                suffix.remove(params.get(j));

                System.out.println("prefix = " + JSON.toJSONString(prefix));
                System.out.println("suffix = " + JSON.toJSONString(suffix));
                execute(suffix);
            }
        }

        System.out.println("mine = " + JSON.toJSONString(mine));

        return params;
    }

}
