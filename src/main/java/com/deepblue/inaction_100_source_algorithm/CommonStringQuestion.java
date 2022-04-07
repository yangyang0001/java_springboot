package com.deepblue.inaction_100_source_algorithm;

/**
 *
 */
public class CommonStringQuestion {

    public static void main(String[] args) {

        String astr = "adefgwgeweg";
        String bstr = "abcdefg";
        String common = "";
        int count = 0;

        for(int i = 0; i < bstr.length(); i++) {
            for(int j = i+1; j <= bstr.length(); j++) {
                String split = bstr.substring(i, j);
                if(astr.contains(split)) {
                    if(common.length() < split.length()) {
                        common = split;
                    } else {
                        continue;
                    }
                }
                count ++;
            }
        }

        System.out.println("count  = " + count);
        System.out.println("common = " + common);


    }
}
