package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_019;

import com.alibaba.fastjson.JSON;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] result = permutation(line);
            System.out.println(JSON.toJSONString(result));
        }

    }

    public static String[] permutation(String line) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < line.length(); i++) {
            set = execute(line, i, set);
        }

        return set.toArray(new String[set.size()]);
    }

    public static Set<String> execute(String line, int index, Set<String> params) {

        Set<String> mine = new HashSet<>();
        if (index <= 0) {
            mine.add(line.substring(0, 1));
            return mine;
        }

        String sub = line.substring(index, index + 1);
//        System.out.println("index = " + index + ", params = " + JSON.toJSONString(params) + ", sub = " + sub);

        for (String param : params) {
            mine.add(sub + param);
            mine.add(param + sub);
            for (int i = 0; i < param.length(); i++) {
                String prefix = param.substring(0, i);
                String suffix = param.substring(i, param.length());
//                System.out.println("prefix = [" + prefix + "], suffix = [" + suffix + "]");
                mine.add(prefix + sub + suffix);
            }
        }

        return mine;
    }


}
