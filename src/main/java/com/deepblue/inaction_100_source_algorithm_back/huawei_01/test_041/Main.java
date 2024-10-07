package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_041;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String param = scanner.nextLine();
            String max = longestPalindrome(param);
            System.out.println(max);
        }
    }

    /**
     * @param param
     * @return
     */
    public static String longestPalindrome(String param) {

        String max = "";
        for (int i = 0; i < param.length(); i++) {
            for (int j = i+1; j <= param.length(); j++) {
                StringBuffer buffer = new StringBuffer();
                String source = param.substring(i, j);
                buffer.append(source);
                String revese  = buffer.reverse().toString();

                if(source.equals(revese) && source.length() > max.length()) {
                    max = source;
                    if(source.length() == param.length()) {
                        return max;
                    }
                }
            }
        }

        return max;
    }

}
