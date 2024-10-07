package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_007;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String execute = execute(line);
            System.out.println(execute);
        }
    }

    public static String execute(String line) {

        if(line.length() <= 8) {
            return "NG";
        }

        int count = 0;
        boolean hasNum = false;
        boolean hasBig = false;
        boolean hasLit = false;
        boolean hasOth = false;
        boolean hasRep = false;

        for (int i = 0; i < line.length(); i++) {
            String temp = line.substring(i, i+1);
            if(temp.compareTo("0") >= 0 && temp.compareTo("9") <= 0) {
                hasNum = true;
            } else if (temp.compareTo("a") >= 0 && temp.compareTo("z") <= 0) {
                hasLit = true;
            } else if (temp.compareTo("A") >= 0 && temp.compareTo("Z") <= 0) {
                hasBig = true;
            } else {
                hasOth = true;
            }

            int last = i+3 >= line.length() ? line.length() : i+3;
            String substring = line.substring(i, last);
            String lefstring = line.substring(last, line.length());

//            System.out.println("substring = " + substring + ", lefstring = " + lefstring);
            if(lefstring.contains(substring)) {
                hasRep = true;
            }
        }

        if(hasNum) {
            count++;
        }

        if(hasBig) {
            count++;
        }

        if(hasLit) {
            count++;
        }

        if(hasOth) {
            count++;
        }

        if(count >= 3 && !hasRep) {
            return "OK";
        } else {
            return "NG";
        }

    }

}
