package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_022;

import java.util.Scanner;

/**
 *
 */
public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String s = longestPalindrome(line);
            System.out.println(s);
        }
    }

    public static String longestPalindrome(String line) {

        String result = "";

        for (int i = 0; i < line.length(); i++) {
            for (int j = i+1; j <= line.length(); j++) {
                StringBuffer buffer = new StringBuffer("");
                buffer.append(line.substring(i, j));

                String source = buffer.toString();
                String target = buffer.reverse().toString();
                if(source.equals(target) && source.length() > result.length()) {
                    result = source;
                }
            }
        }

        return result;
    }

}
