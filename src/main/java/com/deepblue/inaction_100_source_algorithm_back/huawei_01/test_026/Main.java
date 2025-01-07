package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_026;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
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
            int execute = execute(Integer.valueOf(line.split(" ")[0]), Integer.valueOf(line.split(" ")[1]));
            System.out.println(execute);
        }
    }

    public static int execute(int a, int b) {
        List<Integer> alist = new ArrayList<>();
        for (int i = a; i <= a * b; i+=a) {
            alist.add(i);
        }
        List<Integer> blist = new ArrayList<>();
        for (int j = b; j <= a * b; j+=b) {
            blist.add(j);
        }

        System.out.println(JSON.toJSONString(alist));
        System.out.println(JSON.toJSONString(blist));

        return alist.stream().filter(item -> blist.contains(item)).sorted().findFirst().get();
    }
}
