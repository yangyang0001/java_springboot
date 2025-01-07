package com.deepblue.inaction_100_source_algorithm;

/**
 *
 */
public class Test {

    public static String stringchallenge(String str) {
        // code goes here
        for (int i = 0; i < str.length(); i++) {
            String temp = str.substring(0, i) + str.substring(i + 1, str.length());
            String reve = new StringBuilder(temp).reverse().toString();
            if (temp.equals(reve) && temp.length() % 2 != 0) {
                System.out.println(temp + "******" + temp.length() % 2 + ", i = " + i);
                return str.charAt(i) + "";
            }

            for (int j = 0; j < temp.length(); j++) {
                String temp1 = temp.substring(0, j) + temp.substring(j + 1, temp.length());
                String reve1 = new StringBuilder(temp1).reverse().toString();

                System.out.println(temp + "-------" + temp1 + "------" + temp1.length() % 2 + ", i = " + i + ", j = " + j);

                if (temp1.equals(reve1) && temp1.length() % 2 != 0) {
                    return str.charAt(i)  + "," + temp.charAt(j);
                }
            }
        }

        return "NONE";
    }


    public static void main(String[] args) {

        String str = "akjjjhjjj";
//        String str = "mmop";


//        for (int i = 0; i < str.length(); i++) {
//            String substring = str.substring(0, i) + str.substring(i + 1, str.length());
//            System.out.println(substring);
//        }

        System.out.println("result is: " + stringchallenge(str));


    }
}
