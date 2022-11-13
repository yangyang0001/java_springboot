package com.deepblue.inaction_100_source_algorithm_back.huawei.test_002.ipdecimal;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if(line.contains(".")) {
                Long result = ip2decimal(line);
                System.out.println(result);
            } else {
                String result = decimal2ip(line);
                System.out.println(result);
            }

        }

    }

    public static Long ip2decimal(String line) {

        String[] params = line.split("\\.");
        String result = "";

        for(int i = 0; i < params.length; i++) {
            String binaryString = Long.toBinaryString(Long.parseLong(params[i]));
            String prefix = "";
            for(int j = 0; j < 8 - binaryString.length(); j++) {
                prefix += "0";
            }
            binaryString = prefix + binaryString;
            result += binaryString;
        }

        return Long.valueOf(result, 2);
    }


    public static String decimal2ip(String line) {

        String binaryString = Long.toBinaryString(Long.parseLong(line));
        String prefix = "";
        for(int i = 0; i < 32 - binaryString.length(); i++) {
            prefix += "0";
        }
        binaryString = prefix + binaryString;

        Long a = Long.parseLong(binaryString.substring(0,   8), 2);
        Long b = Long.parseLong(binaryString.substring(8,  16), 2);
        Long c = Long.parseLong(binaryString.substring(16, 24), 2);
        Long d = Long.parseLong(binaryString.substring(24, 32), 2);

        return a + "." + b + "." + c + "." + d;
    }

}
