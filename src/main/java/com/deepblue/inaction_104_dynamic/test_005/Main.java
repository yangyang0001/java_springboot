package com.deepblue.inaction_104_dynamic.test_005;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * 给你一个输入字符串 (s) 和一个字符模式 (p) ，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配：
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符序列（包括空字符序列）。
 * 判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。
 */
public class Main {

    public static void main(String[] args) {
        String line = "cb";
        String pattern = "?a";

        boolean flag = match(line, pattern);
        System.out.println(flag);
    }

    public static boolean match(String line, String pattern) {
        return line.matches(pattern);
    }
}
