package com.deepblue.inaction_100_source_algorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 */
public class Test001 {

    public static void main(String[] args) {

//        Scanner input = new Scanner(System.in);
//        String first = input.nextLine();
//        String[] items = first.split(" ");
//        Integer count = Integer.valueOf(items[0]);
//        Integer opera = Integer.valueOf(items[1]);
//
//        String secod = input.nextLine();
//        String[] scores = secod.split(" ");
//
//        String[] operators = new String[opera];
//
//        for(int i = 0; i < opera; i++) {
//            operators[i] = input.nextLine();
//        }
//
//        System.out.println("-------------------------------------------------");
//
//        for(int i = 0; i < operators.length; i++) {
//            String[] details = operators[i].split(" ");
//            Integer begin = Integer.valueOf(details[1]) - 1;
//            Integer end   = Integer.valueOf(details[2]) - 1;
//            if(details[0].equals("Q") && begin < end) {
//                Object[] subscores = Arrays.stream(Arrays.copyOfRange(scores, begin, end + 1)).map(item -> Integer.valueOf(item)).toArray();
//                Arrays.sort(subscores);
//                System.out.println(subscores[subscores.length-1]);
//            } else if(details[0].equals("U")) {
//                scores[begin] = String.valueOf(Integer.valueOf(details[2]));
//            }
//        }


        String a = "ABC";
        if(a.length() % 2 == 0) {
            int middle = a.length() / 2;
            String prefix = a.substring(0, middle + 1);
            String suffix = a.substring(middle + 1, a.length());
            System.out.println(prefix);
            System.out.println(suffix);

        } else {
            int middle = a.length() / 2 + 1;
            String prefix = a.substring(0, middle - 1);
            String midfix = a.substring(middle - 1, middle);
            String suffix = a.substring(middle, a.length());
            System.out.println("suffix  = :" + suffix);
            System.out.println("midfix  = :" + midfix);
            System.out.println("prefix  = :" + prefix);
            
        }





    }
}
