package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_012;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {

            String line = scanner.nextLine();

            int result = execute(line);

            System.out.println(result);

        }

    }

    public static int execute(String line) {
        int max = 0;

        if(!isExist(line)) {
            return 0;
        }

        for(int i = 0; i <= line.length() - 5; i++) {
            for(int j = i + 5; j <= line.length(); j++) {
                String substring = line.substring(i, j);
                if(isExist(substring) && isBeautiful(substring)) {
                    if(substring.length() > max) {
                        max = substring.length();
                    }
                } else {
                    continue;
                }
            }
        }

        return max;
    }

    public static boolean isExist(String line) {
        if(line.length() < 5) {
            return false;
        }

        if (line.contains("a") && line.contains("e") && line.contains("i") && line.contains("i") && line.contains("u")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBeautiful(String line) {

        String param = line.replaceAll("a", "");
        param = param.replaceAll("e", "");
        param = param.replaceAll("i", "");
        param = param.replaceAll("o", "");
        param = param.replaceAll("u", "");
        if(param.length() > 0) {
            return false;
        }

        int aLastIndex = line.lastIndexOf("a");
        int eFirsIndex = line.indexOf("e");

        int eLastIndex = line.lastIndexOf("e");
        int iFirsIndex = line.indexOf("i");

        int iLastIndex = line.lastIndexOf("i");
        int oFirsIndex = line.indexOf("o");

        int oLastIndex = line.lastIndexOf("o");
        int uFirsIndex = line.indexOf("u");

        if(aLastIndex < eFirsIndex && eLastIndex < iFirsIndex && iLastIndex < oFirsIndex && oLastIndex < uFirsIndex) {
            return true;
        } else {
            return false;
        }
    }

}
