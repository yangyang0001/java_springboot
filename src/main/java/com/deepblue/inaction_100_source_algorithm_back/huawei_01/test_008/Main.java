package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_008;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String execute = execute(line);
            System.out.println(execute);
        }

    }


    public static String execute(String line) {

        Map<String, Integer> map = new HashMap<>();

        String[] split = line.split("");
        for(String s : split) {
            if(map.get(s) == null) {
                map.put(s, 1);
            } else {
                map.put(s, map.get(s) + 1);
            }
        }

        int min = map.values().stream().min(Comparator.comparingInt(Integer::intValue)).get().intValue();
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue().intValue() == min) {
                line = line.replaceAll(entry.getKey(), "");
            }
        }

        return line;
    }

}
