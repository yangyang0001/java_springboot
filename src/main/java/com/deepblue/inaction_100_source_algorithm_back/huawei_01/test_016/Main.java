package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_016;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String result = execute(line);
            System.out.println(result);
        }

    }

    public static String execute(String line) {
        List<String> list = new ArrayList<>();

        String[] params = line.split(" ");
        String index  = params[params.length - 1];
        String target = params[params.length - 2];

        for (int i = 1; i < params.length - 2; i++) {
            if(isBrother(params[i], target)) {
                list.add(params[i]);
            }
        }

        list.sort(String::compareTo);
        return list.size() + (list.size() < Integer.valueOf(index) ? "" : "\n" + list.get(Integer.valueOf(index) - 1));
    }

    public static boolean isBrother(String source, String target) {

        if(source.length() != target.length() || source.equals(target)) {
            return false;
        }

        char[] s = source.toCharArray();
        char[] t = target.toCharArray();

        Arrays.sort(s);
        Arrays.sort(t);

        return new String(s).equals(new String(t));
    }
}
