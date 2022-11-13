package com.deepblue.inaction_100_source_algorithm_back;

/**
 *
 */
public class SquareRoot {

    public static void main(String[] args) {

        int number = 10;
        double deviation = 0.00000000000001d;

        double square = square(deviation, number, 0, number);

        System.out.println(Math.pow(square, 2));

    }

    public static double square(double deviation, double number, double begin, double end) {

        double half = (begin + end) / 2;

        if(number - Math.pow(half, 2) > deviation) {
            return square(deviation, number, half, end);
        }

        if(Math.pow(half, 2) - number > deviation) {
            return square(deviation, number, begin, half);
        }

        return half;
    }
}
