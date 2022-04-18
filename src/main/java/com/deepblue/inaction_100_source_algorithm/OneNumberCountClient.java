package com.deepblue.inaction_100_source_algorithm;

/**
 *
 */
public class OneNumberCountClient {

    public static void main(String[] args) {

        int number = 7;
        int count = count(number);
        System.out.println(count);

    }

    public static int count(int number) {
        int count = 0;

        while(number > 0) {
            if((number & 1) == 1) {
                count ++;
            }
            number = number >> 1;
        }

        return count;
    }

}
