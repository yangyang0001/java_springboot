package com.deepblue.inaction_104_dynamic.test_002;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */
public class Main {

    public static List<String> result = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {

            String line = scanner.nextLine();

            int count = Integer.valueOf(line);

            result = new ArrayList<>();

            execute(count, count, "");

            System.out.println(JSON.toJSONString(result));

        }

    }

    public static void execute(int left, int right, String current) {

        if(left == 0 && right == 0) {
            result.add(current);
            return;
        }

        if(left > 0) {
            execute(left - 1, right, current + "(");
        }

        if(right > left) {
            execute(left, right - 1, current + ")");
        }

    }

}
