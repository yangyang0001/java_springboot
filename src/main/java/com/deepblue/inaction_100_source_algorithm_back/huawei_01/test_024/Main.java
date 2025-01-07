package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_024;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Integer  catego = Integer.valueOf(scanner.nextLine());
        String[] weight = scanner.nextLine().split(" ");
        String[] counts = scanner.nextLine().split(" ");

        int result = execute(weight, counts);
        System.out.println(result);
    }

    public static int execute(String[] weight, String[] counts) {
        StringBuffer buffer = new StringBuffer("");
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < Integer.valueOf(counts[i]); j++) {
                buffer.append(String.valueOf(i));
            }
        }

        String line = buffer.toString();
        System.out.println("line = " + line);

        Set<String> result = new HashSet<>();
        Set<String> mine = new HashSet<>();
        for (int i = 0; i < line.length(); i++) {
            mine = compute(line, i, mine);
            result.addAll(mine);
        }

        System.out.println("result = " + JSON.toJSONString(result));
        return result.size();
    }


    public static Set<String> compute(String line, int index, Set<String> params) {
        System.out.println("index = " + index + ", params = " + JSON.toJSONString(params));
        Set<String> mine = new HashSet<>();
        if(index == 0) {
            for (int i = 0; i < line.length(); i++) {
                mine.add(String.valueOf(line.substring(i, i+1)));
            }
            return mine;
        }

        String sub = line.substring(index, index + 1);
        for (String param : params) {
            for (int i = 0; i < param.length(); i++) {
                if (param.length() > line.length()) {
                    return params;
                }

                String cons = param.substring(0, i) + sub + param.substring(i, param.length());
                char[] array1 = cons.toCharArray();
                Arrays.sort(array1);
                String sort = new String(array1);

                boolean exist = false;
                String[] split = sort.split("");
//                if(line.contains(sort)) {
                    mine.add(new String(array1));
//                }
            }
        }

        return mine;
    }

}
