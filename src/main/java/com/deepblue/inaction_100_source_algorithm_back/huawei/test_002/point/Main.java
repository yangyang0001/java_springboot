package com.deepblue.inaction_100_source_algorithm_back.huawei.test_002.point;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String[] params = scanner.nextLine().split(";");
            String execute = execute(params);
            System.out.println(execute);
        }

    }

    public static String execute(String[] params) {

        int[] result = {0, 0};

        for(int i = 0; i < params.length; i++) {
            String prefix = "";
            String suffix = "";
            int number = 0;

            try {
                prefix = params[i].substring(0, 1);
                suffix = params[i].substring(1, params[i].length());
                number = Integer.valueOf(suffix);
            } catch (Exception e) {
                System.out.println("无效:" + params[i]);
                continue;
            }

            if("A".equals(prefix)) {
                // -x
                result[0] -= number;

            } else if ("D".equals(prefix)) {
                // +x
                result[0] += number;

            } else if ("W".equals(prefix)) {
                // +y
                result[1] += number;
            } else if ("S".equals(prefix)) {
                // -y
                result[1] -= number;
            }
        }

        return result[0] + "," + result[1];
    }



}
