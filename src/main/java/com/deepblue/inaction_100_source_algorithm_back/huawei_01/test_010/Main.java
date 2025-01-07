package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_010;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = Integer.parseInt(scanner.nextLine());
        String param = scanner.nextLine();
        int order = Integer.parseInt(scanner.nextLine());

        String result = execute(count, param, order);
        System.out.println(result);

    }

    public static String execute(int count, String param, int order) {
        String[] split = param.split(" ");
        List<Integer> collect = Stream.of(split).map(item -> Integer.valueOf(item)).collect(Collectors.toList());

        if(order == 0) {
            collect.sort((o1, o2) -> o1 - o2);
        } else {
            collect.sort((o1, o2) -> o2 - o1);
        }

        StringBuffer buffer = new StringBuffer("");
        collect.forEach(item -> buffer.append(item).append(" "));
        return buffer.toString();
    }
}
