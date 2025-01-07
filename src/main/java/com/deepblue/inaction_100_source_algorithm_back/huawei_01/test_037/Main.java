package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_037;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        String line = "";

        String[] split = line.split("");
        List<String> collect = Arrays.stream(split).collect(Collectors.toList());
        collect.toArray(new String[collect.size()]);


    }
}
