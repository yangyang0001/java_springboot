package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_018;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Iterator;
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

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

//            第一种 想复杂了
//            int max = execute(line);
//            System.out.println(max);

//            第二种 简便方法
            int max = maxDepth(line);
            System.out.println(max);
        }

    }

    public static int execute(String line) {
        String param = line.substring(5, line.length()-1);
        String[] split = param.split("");

        StringBuffer buffer = new StringBuffer("");
        for (int i = 0; i < split.length; i++) {
            if(split[i].equals("(") || split[i].equals(")")) {
                buffer.append(split[i]);
            }
        }

        String stack = buffer.toString();
        return maxDepth(stack, 0);
    }

    public static int maxDepth(String stack, int count) {
        if(stack.length() == 0) {
            return count;
        }
        stack = stack.replaceAll("\\(\\)", "");
        count++;
        return maxDepth(stack, count);
    }


    public static int maxDepth(String line) {
        char[] array = line.toCharArray();
        int deep = 0;
        int max = 0;

        for (int i = 0; i < array.length; i++) {
            if(String.valueOf(array[i]).equals("(")) {
                deep ++;
                max = deep > max ? deep : max;
            } else if(String.valueOf(array[i]).equals(")")) {
                deep --;
            }
        }

        return max;
    }


}
