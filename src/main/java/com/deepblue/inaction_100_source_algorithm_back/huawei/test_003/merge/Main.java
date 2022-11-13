package com.deepblue.inaction_100_source_algorithm_back.huawei.test_003.merge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 */
public class Main {


    public static void main(String[] args) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        Scanner scanner = new Scanner(System.in);
        int count = Integer.valueOf(scanner.nextLine());

        for(int i = 0; i < count; i++) {
            String line = scanner.nextLine();
            String[] array = line.split(" ");
            Integer key = Integer.valueOf(array[0]);
            Integer val = Integer.valueOf(array[1]);

            if(map.get(key) == null) {
                map.put(key, val);
            } else {
                map.put(key, map.get(key) + val);
            }
        }

        List<Map.Entry<Integer, Integer>> collect = map.entrySet().stream()
                .sorted((element0, element1) -> element0.getKey().compareTo(element1.getKey())).collect(Collectors.toList());

        collect.stream().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
    }
}
