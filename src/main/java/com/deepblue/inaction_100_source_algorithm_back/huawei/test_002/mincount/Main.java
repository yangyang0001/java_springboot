package com.deepblue.inaction_100_source_algorithm_back.huawei.test_002.mincount;

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

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String result = execute(line);
            System.out.println(result);
        }

    }

    public static String execute(String line) {

        Map<String, Integer> countmap = new HashMap<String, Integer>();

        for(int i = 0; i < line.length(); i++) {
            String temp = line.substring(i, i+1);
            if(countmap.get(temp) == null) {
                countmap.put(temp, 1);
            } else {
                countmap.put(temp, countmap.get(temp) + 1);
            }
        }

        List<Map.Entry<String, Integer>> templist = countmap.entrySet().stream()
                .sorted((en0, en1) -> en0.getValue().compareTo(en1.getValue())).collect(Collectors.toList());

        int min = templist.get(0).getValue();

        for(int i = 0; i < templist.size(); i++) {
            int value = templist.get(i).getValue();
            if(min >= value) {
                line = line.replaceAll(templist.get(i).getKey(), "");
            } else {
                break;
            }
        }

        return line;
    }
}
