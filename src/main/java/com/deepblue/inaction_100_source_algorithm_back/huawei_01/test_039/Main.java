package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_039;

import com.alibaba.fastjson.JSON;

import java.util.Scanner;

/**
 * ABCDEFGHIJK 5
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            execute(line);
        }
    }

    public static void execute(String params) {

        String[] split = params.split(" ");
        String boxs = split[0];
        Integer rows = Integer.valueOf(split[1]);
        int cols = boxs.length() % rows == 0 ? boxs.length() / rows : boxs.length() / rows + 1;

        String[][] mine = new String[rows][cols];

        int count = 0;
        for (int i = 0; i < boxs.length(); i+=cols, count++) {
            int start = i;
            int end = i + cols > boxs.length() ? boxs.length() : i + cols;
            String param = boxs.substring(start, end);

            StringBuffer buffer = new StringBuffer();
            if(count % 2 != 0) {
                buffer.append(param);
                param = buffer.reverse().toString();
            }

            for (int j = 0; j < cols; j++) {
                int sub = j + 1;
                if(sub > param.length()) {
                    continue;
                } else {
                    mine[count][j] = param.substring(j, sub);
                }
            }
        }

        System.out.println("mine = " + JSON.toJSONString(mine));

        for (int i = 0; i < mine[0].length; i++) {
            StringBuffer buffer = new StringBuffer("");
            for (int j = 0; j < mine.length; j++) {
                if(!"".equals(mine[j][i]) && mine[j][i] != null) {
                    buffer.append(mine[j][i]);
                } else {
                    buffer.append(" ");
                }
            }
            System.out.println(buffer.toString());
        }
    }
}
