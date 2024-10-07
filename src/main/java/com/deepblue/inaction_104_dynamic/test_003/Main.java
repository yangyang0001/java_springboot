package com.deepblue.inaction_104_dynamic.test_003;

import java.util.Scanner;

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            int result = execute(line);

            System.out.println(result);
        }

    }

    public static int execute(String param) {
        int max = 0;
        int[] array = new int[param.length()];

        for (int i = 1; i < param.length(); i++) {
            if (param.charAt(i) == ')') {
                if (param.charAt(i - 1) == '(') {
                    array[i] = (i - 2 > 0 ? array[i - 2] : 0) + 2;
                } else if (i - array[i - 1] > 0 && param.charAt(i - array[i - 1] - 1) == '(') {
                    array[i] = array[i - 1] + 2 + (i - array[i - 1] - 2 >= 0 ? array[i - array[i - 1] - 2] : 0);
                }
            }
            max = Math.max(max, array[i]);
        }

        return max;
    }


}
