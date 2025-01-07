package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_031;

import java.util.Scanner;

/**
 * 加密方法为:
 * 当内容是英文字母时则用该英文字母的后一个字母替换, 同时字母变换大小写, 如 字母a时 则 替换为B; 字母Z时则替换为a;
 * <p>
 * 当内容是数字时则把该数字加1, 如0替换1, 1替换2, 9替换0;
 * <p>
 * 其他字符不做变化。
 * <p>
 * 解密方法为加密的逆过程。
 */
public class Main {

    public static String LITT = "abcdefghijklmnopqrstuvwxyz";
    public static String GREA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String NUME = "0123456789";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String first = scanner.nextLine();
            String secnd = scanner.nextLine();

            String encode = encode(first);
            System.out.println(encode);

            String decode = decode(secnd);
            System.out.println(decode);
        }

    }

    public static String encode(String line) {

        StringBuffer buffer = new StringBuffer("");
        String[] split = line.split("");

        for (int i = 0; i < split.length; i++) {
            String source = split[i];
            String target = source;

            if (source.compareTo("a") >= 0 && source.compareTo("z") <= 0) {
                int index = (LITT.indexOf(source) + 1) % LITT.length();
                target = LITT.substring(index, index + 1).toUpperCase();
            }

            if (source.compareTo("A") >= 0 && source.compareTo("Z") <= 0) {
                int index = (GREA.indexOf(source) + 1) % GREA.length();
                target = GREA.substring(index, index + 1).toLowerCase();
            }

            if (source.compareTo("0") >= 0 && source.compareTo("9") <= 0) {
                int index = (NUME.indexOf(source) + 1) % NUME.length();
                target = NUME.substring(index, index + 1);
            }

            buffer.append(target);
        }

        return buffer.toString();
    }

    public static String decode(String line) {
        StringBuffer buffer = new StringBuffer("");
        String[] split = line.split("");

        for (int i = 0; i < split.length; i++) {
            String source = split[i];
            String target = source;

            if (source.compareTo("a") >= 0 && source.compareTo("z") <= 0) {
                int index = (LITT.indexOf(source) + LITT.length() - 1) % LITT.length();
                target = LITT.substring(index, index + 1).toUpperCase();
            }

            if (source.compareTo("A") >= 0 && source.compareTo("Z") <= 0) {
                int index = (GREA.indexOf(source) + GREA.length() - 1) % GREA.length();
                target = GREA.substring(index, index + 1).toLowerCase();
            }

            if (source.compareTo("0") >= 0 && source.compareTo("9") <= 0) {
                int index = (NUME.indexOf(source) + NUME.length() - 1) % NUME.length();
                target = NUME.substring(index, index + 1);
            }

            buffer.append(target);
        }

        return buffer.toString();
    }


}
