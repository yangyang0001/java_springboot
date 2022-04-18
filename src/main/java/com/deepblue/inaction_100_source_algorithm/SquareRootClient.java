package com.deepblue.inaction_100_source_algorithm;

/**
 *
 */
public class SquareRootClient {

    public static void main(String[] args) {

        double number = 10;
        System.out.println(square(number));

        System.out.println(Math.pow(3.1622772216796875d, 2d));

    }



    public static double square(double number) {

        double result = 0;

        for(double i = 0; i < Double.MAX_VALUE; i++) {
            if(i * i == number) {
                result = i;
                break;
            } else if (i * i > number) {
                result = half(number, i - 1, i);
                break;
            }
        }

        return result;
    }

    public static double half(double number, double begin, double end) {

        double middle = (begin + end) / 2;
        double result = middle * middle;

        if(result == number) {
            return middle;
        } else if(result < number) {
            if(String.valueOf(middle).length() >= 18) {
                return middle;
            }
            return half(number, middle, end);
        } else {
            if(String.valueOf(middle).length() >= 18) {
                return middle;
            }
            return half(number, begin, middle);
        }

    }

}
