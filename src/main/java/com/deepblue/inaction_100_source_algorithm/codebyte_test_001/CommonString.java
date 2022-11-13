package com.deepblue.inaction_100_source_algorithm.codebyte_test_001;

/**
 *
 */

import java.util.*;
import java.util.stream.Collectors;

class CommonString {

    public static String FindIntersection(String strArr) {
        // code goes here
        String[] split = strArr.split("\"");

        String first = strArr.split("\"")[1];
        String secod = strArr.split("\"")[3];
//        String first = strArr[0];
//        String secod = strArr[1];

        List<String> firstArray = Arrays.stream(first.split(",")).collect(Collectors.toList());
        List<String> secodArray = Arrays.stream(secod.split(",")).collect(Collectors.toList());

        List<String> collect = firstArray.stream().filter(item -> secodArray.contains(item)).collect(Collectors.toList());





        return collect.toString();


    }


    public static String ArrayChallenge(String[] strArr) {
        // code goes here
        Map<String, Integer> rootMap = new HashMap<String, Integer>();
        for(int i = 0; i < strArr.length; i++) {
            String root = strArr[i].split(",")[1];
            if(rootMap.get(root) == null) {
                rootMap.put(root, 1);
            } else if(rootMap.get(root) >= 2){
                return "false";
            } else {
                rootMap.put(root, rootMap.get(root) + 1);
            }
        }

        return "true";
    }

    public static void main (String[] args) {
        // keep this function call here
        Scanner s = new Scanner(System.in);
        System.out.print(FindIntersection(s.nextLine()));
    }

}
