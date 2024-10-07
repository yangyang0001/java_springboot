package com.deepblue.inaction_104_dynamic.test_001;

import java.util.Scanner;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String result = execute(line);
            System.out.println(result);
        }
    }

    public static String execute(String param) {
        String result = "";
        for (int i = 0; i < param.length(); i++) {
            for (int j = i + 1; j <= param.length(); j++) {
                String source = param.substring(i, j);
                System.out.println("source = " + source);
                StringBuffer buffer = new StringBuffer(source);
                if (source.equals(buffer.reverse().toString()) && result.length() < source.length()) {
                    result = source;
                }
            }
        }

        return result;
    }


}