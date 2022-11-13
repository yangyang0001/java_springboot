package com.deepblue.inaction_100_source_algorithm_back.huawei.test_002.password;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String flag = checkPass(line);
            System.out.println(flag);
        }

    }

    public static String checkPass(String password) {

        if(password.length() <= 8) {
            System.out.println(password + " is not allow length!");
            return "NG";
        }

        if(!categoryLegal(password)) {
            System.out.println(password + " is not allow category!");
            return "NG";
        }

        if(!repeateLegal(password)) {
            System.out.println(password + " is not allow repeate!");
            return "NG";
        }

        return "OK";
    }

    /**
     * 种类合法校验
     *
     * @param password
     * @return
     */
    public static boolean categoryLegal(String password) {
        int upper = 0;
        int lower = 0;
        int numbe = 0;
        int other = 0;

        String[] params = password.split("");

        for(int i = 0; i < params.length; i++) {
            if(params[i].compareTo("A") >= 0 && params[i].compareTo("Z") <= 0) {
                upper = 1;
            } else if(params[i].compareTo("a") >= 0 && params[i].compareTo("z") <= 0) {
                lower = 1;
            } else if(params[i].compareTo("0") >= 0 && params[i].compareTo("9") <= 0) {
                numbe = 1;
            } else {
                other = 1;
            }
        }

        return upper + lower + numbe + other >= 3;
    }

    /**
     * 重复字符串合法校验
     *
     * 12345678
     * 021Abc9000
     * 021Abc9Abc1
     * 021ABC9000
     * 021$bc9000
     *
     * @param password
     * @return
     *
     */
    public static boolean repeateLegal(String password) {

        for(int i = 0; i < password.length() - 2; i++) {
            String substring = password.substring(i, i+3);
            System.out.println(substring);
            int index0 = password.indexOf(substring);
            int index1 = password.lastIndexOf(substring);
            if(index0 != index1) {
                return false;
            }
        }

        return true;
    }

}
