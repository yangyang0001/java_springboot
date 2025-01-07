package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_023;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String s = minWindow(line.split(" ")[0], line.split(" ")[1]);
            System.out.println("s = " + s);
        }

    }

    /**
     * ADOBECODEBANC ABC
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <= s.length(); j++) {
                StringBuffer buffer = new StringBuffer("");
                buffer.append(s.substring(i, j));
                if(isExist(buffer.toString(), t)) {
                    if("".equals(result)) {
                        result = buffer.toString();
                    } else if(buffer.toString().length() < result.length()){
                        result = buffer.toString();
                    }
                }
            }
        }

        return result;
    }

    /**
     * ADOBECODEBANC ABC
     * aaaaaaaaaaaabbbbbcdd abcdd
     * bbaa aba
     * @param s
     * @param t
     * @return
     */
    public static boolean isExist(String s, String t) {

        if(s.length() >= t.length()) {
            Set<String> source = Arrays.stream(s.split("")).collect(Collectors.toSet());
            Set<String> target = Arrays.stream(t.split("")).collect(Collectors.toSet());

            if(source.containsAll(target)) {
                if(s.length() == t.length()) {
                    Set<String> mine = new HashSet<>();
                    for (int i = 0; i < s.length(); i++) {
                        mine = execute(s.substring(i, i+1), i, t.length(), mine);
                    }

                    System.out.println("source = " + source + ", target = " + target + ", mine = " + JSON.toJSONString(mine));
                    if(mine.contains(t)) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }


        return false;
    }

    public static Set<String> execute(String s, int index, int length, Set<String> params) {

        Set<String> mine = new HashSet<>();
        if(index == 0) {
            mine.add(s);
            return mine;
        }

        for (String param : params) {
            if (param.length() == length) {
                return params;
            }

            mine.add(s + param);
            mine.add(param + s);

            for (int i = 0; i < param.length(); i++) {
                mine.add(param.substring(0, i) + s + param.substring(i, param.length()));
            }
        }

        return mine;
    }

}
