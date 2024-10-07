package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_006;

import org.apache.commons.lang3.StringUtils;
import java.util.Scanner;

/**
 *
 */
public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String location = execute(line);
            System.out.println(location);
        }

    }


    public static String execute(String line) {
        int[] arr = new int[2];
        String[] split = line.trim().split(";");

        for (String s : split) {
            if(s.length() <= 2 || s.lastIndexOf("A") > 0 || s.lastIndexOf("D") > 0 || s.lastIndexOf("W") > 0 || s.lastIndexOf("S") > 0) {
                continue;
            }

            String temp = s.substring(1, 2).toUpperCase();
            if("ABCDEFGHIJKLMNOPQRSTWVUXYZ".contains(temp)) {
                continue;
            }

            String num = s.substring(1, s.length());
            if(s.lastIndexOf("A") == 0) {
                arr[0] -= Integer.valueOf(num);
            }
            if(s.lastIndexOf("D") == 0) {
                arr[0] += Integer.valueOf(num);
            }
            if(s.lastIndexOf("W") == 0) {
                arr[1] += Integer.valueOf(num);
            }
            if(s.lastIndexOf("S") == 0) {
                arr[1] -= Integer.valueOf(num);
            }
        }

        return "(" + arr[0] + "," + arr[1] + ")";
    }


}
