package com.deepblue.inaction_103_mine.test_002;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
 *
 * 请你返回字符串 s 的 能量。
 *
 *
 * 示例 1：
 *
 * 输入：s = "leetcode"
 * 输出：2
 * 解释：子字符串 "ee" 长度为 2 ，只包含字符 'e' 。
 * 示例 2：
 *
 * 输入：s = "abbcccddddeeeeedcba"
 * 输出：5
 * 解释：子字符串 "eeeee" 长度为 5 ，只包含字符 'e' 。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 500
 * s 只包含小写英文字母。
 */
public class Main {

    public static void main(String[] args) {
//        String param = "leetcode";
        String param = "abbcccddddeeeeedcba";
        int max = pow(param);
        System.out.println(max);
    }

    public static int pow(String param) {
        int max = 0;
        for (int i = 0; i < param.length(); i++) {
            for (int j = i+1; j <= param.length(); j++) {
                String line = param.substring(i, j);
                if(isLegal(line) && max < line.length()) {
                    max = line.length();
                }
            }
        }
        return max;
    }

    public static boolean isLegal(String param) {
        String[] split = param.split("");
        Set<String> collect = Arrays.stream(split).collect(Collectors.toSet());
        if(collect.size() == 1) {
            return true;
        }
        return false;
    }



}
