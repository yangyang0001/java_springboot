package com.deepblue.inaction_100_source_algorithm_back.huawei.test_004.maxdepth;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int depth = maxDepth(line);
            System.out.println(depth);
        }

    }

    public static int maxDepth(String line) {
        char[] array = line.toCharArray();
        int dep = 0;
        int max = 0;

        for (int i = 0; i < array.length; i++) {
            if(String.valueOf(array[i]).equals("(")) {
                dep ++;
                max = dep > max ? dep : max;
            } else if(String.valueOf(array[i]).equals(")")) {
                dep--;
            }
        }
        return max;
    }

}
