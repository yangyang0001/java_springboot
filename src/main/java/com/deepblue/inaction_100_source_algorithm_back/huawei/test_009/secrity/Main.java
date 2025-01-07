package com.deepblue.inaction_100_source_algorithm_back.huawei.test_009.secrity;


import java.util.Scanner;

/**
 *
 */
public class Main {

    public static char[] lowers = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    public static char[] uppers = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    public static char[] number = "0123456789".toCharArray();


    /**
     * abcdefgAZaz09
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String sourceLine = scanner.nextLine();
            String encode = encode(sourceLine);
            System.out.println(encode);
            String encodeLine = scanner.nextLine();
            String decode = decode(encodeLine);
            System.out.println(decode);
        }

    }

    /**
     * 加密方法为:
     * 当内容是英文字母时则用该英文字母的后一个字母替换，同时字母变换大小写,如字母a时则替换为B；字母Z时则替换为a；
     * 当内容是数字时则把该数字加1，如0替换1，1替换2，9替换0；其他字符不做变化。
     *
     * 解密方法为: 加密的逆过程。
     *
     * @param sourceLine
     * @return
     */
    public static String encode(String sourceLine) {

        char[] array = sourceLine.toCharArray();
        StringBuilder builder = new StringBuilder("");

        char base, rest;
        for (int i = 0; i < array.length; i++) {
            base = array[i];
            if(base >= 'a' && base <= 'z') {
                rest = lowers[(base - 97 + 1) % 26];
                builder.append(String.valueOf(rest).toUpperCase());
            } else if (base >= 'A' && base <= 'Z') {
                rest = uppers[(base - 65 + 1) % 26];
                builder.append(String.valueOf(rest).toLowerCase());
            } else if (base >= '0' && base <= '9') {
                rest = number[(base - 48 + 1) % 10];
                builder.append(String.valueOf(rest));
            } else {
                builder.append(String.valueOf(base));
            }
        }

        return builder.toString();
    }

    public static String decode(String encodeLine) {
        char[] array = encodeLine.toCharArray();
        StringBuilder builder = new StringBuilder("");

        char base, rest;
        for (int i = 0; i < array.length; i++) {
            base = array[i];
            if(base >= 'a' && base <= 'z') {
                rest = lowers[(base - 97 + 25) % 26];
                builder.append(String.valueOf(rest).toUpperCase());
            } else if (base >= 'A' && base <= 'Z') {
                rest = uppers[(base - 65 + 25) % 26];
                builder.append(String.valueOf(rest).toLowerCase());
            } else if (base >= '0' && base <= '9') {
                rest = number[(base - 48 + 9) % 10];
                builder.append(String.valueOf(rest));
            } else {
                builder.append(String.valueOf(base));
            }
        }

        return builder.toString();
    }

}