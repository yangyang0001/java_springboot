package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_009;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String result = execute(line);
            System.out.println(result);
        }

    }

    public static String execute(String line) {

        StringBuffer buffer = new StringBuffer("");
        if(line.contains(".")) {
            String[] split = line.split("\\.");
            for(String s : split) {
                String bs = Long.toBinaryString(Long.valueOf(s));
                for (int i = 0; i < 8 - bs.length(); i++) {
                    buffer.append("0");
                }
                buffer.append(bs);
            }

            return String.valueOf(Long.parseLong(buffer.toString(), 2));
        } else {
            String bs = Long.toBinaryString(Long.valueOf(line));
            for (int i = 0; i < 32 - bs.length(); i++) {
                buffer.append("0");
            }
            buffer.append(bs);

            String last = buffer.toString();
            StringBuffer result = new StringBuffer("");
            result.append(Long.parseLong(last.substring(0,8), 2)).append(".");
            result.append(Long.parseLong(last.substring(8,16), 2)).append(".");
            result.append(Long.parseLong(last.substring(16,24), 2)).append(".");
            result.append(Long.parseLong(last.substring(24,32), 2));

            return result.toString();
        }
    }
}
