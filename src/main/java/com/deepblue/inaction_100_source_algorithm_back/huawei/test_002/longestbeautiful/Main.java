package com.deepblue.inaction_100_source_algorithm_back.huawei.test_002.longestbeautiful;

import java.util.Scanner;

/**
 *
 */
public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String result = longestBeautifulSubstring(line);
            System.out.println(result);
        }

    }

    /**
     * aeiaaioaaaaeiiiiouuuooaauuaeiu
     *
     * @param param
     * @return
     */
    public static String longestBeautifulSubstring(String param) {

        String reuslt = "";

        if(precheck(param)) {
            for(int i = 0; i < param.length(); i++) {
                for (int j = i+5; j <= param.length(); j++) {
                    String substring = param.substring(i, j);
                    if(precheck(substring) && nextcheck(substring)) {
                        int eIndex = substring.indexOf("e");
                        int iIndex = substring.indexOf("i");
                        int oIndex = substring.indexOf("o");
                        int uIndex = substring.indexOf("u");

                        int alIndex = substring.lastIndexOf("a");
                        int elIndex = substring.lastIndexOf("e");
                        int ilIndex = substring.lastIndexOf("i");
                        int olIndex = substring.lastIndexOf("o");

                        if(alIndex < eIndex && elIndex < iIndex && ilIndex < oIndex && olIndex < uIndex) {
                            if(reuslt.length() < substring.length()) {
                                reuslt = substring;
                            }
                        }
                    }
                }
            }
        }

        return reuslt.length() == 0 ? "0" : reuslt;
    }

    public static boolean precheck(String param) {

        boolean precheck = param.contains("a") && param.contains("e") && param.contains("i") && param.contains("o")
                && param.contains("u");

        return precheck;

    }

    /**
     * aeiaaioaaaaeiiiiouuuooaauuaeiu
     *
     * @param param
     * @return
     */
    public static boolean nextcheck(String param) {

        String checkstring = param.replaceAll("a", "")
                .replaceAll("e", "")
                .replaceAll("i", "")
                .replaceAll("o", "")
                .replaceAll("u", "");

        boolean nextcheck = checkstring.length() == 0;

        return nextcheck;
    }




}

