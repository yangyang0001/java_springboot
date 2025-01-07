package com.deepblue.inaction_100_source_algorithm.codebyte_test_002;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
public class CommonMinString {

    public static void main(String[] args) {
//        String[] strArr = new String[] {"ahffaksfajeeubsne", "jefaa"};
        String[] strArr = new String[] {"aaabaaddae", "aed"};
        String result = shortestString(strArr);
        System.out.println("result is " + result);
    }

    public static String shortestString(String[] strArr) {
        List<String> n = Arrays.asList(strArr[0].split(""));
        List<String> k = Arrays.stream(strArr[1].split("")).collect(Collectors.toSet()).stream().collect(Collectors.toList());

        String minResult = strArr[0];

        for(int i = 0; i < n.size(); i++) {
            for (int j = i+k.size(); j <= n.size(); j++) {
                List<String> subList = n.subList(i, j);
                System.out.println("minResult = " + minResult + ", length = " + minResult.length() + ", subList is :" + JSON.toJSONString(subList));
                if(subList.containsAll(k) && subList.size() < minResult.length()) {
                    minResult = subList.toString().replace("[", "").replace("]", "")
                            .replace(", ", "").trim();;
                }
            }
        }

        return minResult;
    }

}
