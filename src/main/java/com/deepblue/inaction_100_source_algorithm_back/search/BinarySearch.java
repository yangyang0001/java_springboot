package com.deepblue.inaction_100_source_algorithm_back.search;

/**
 *
 */
public class BinarySearch {

    public static void main(String[] args) {

        int[] array = {1, 3, 5, 7, 9, 11};

        int number = 10;

        int index = search(number, array, 0, array.length);

        if (index < 0) {
            System.out.println("result index = " + index);
        } else {
            System.out.println("result index = " + index + ", array[" + index + "] = " + array[index]);
        }

    }


    public static int search(int number, int[] array, int start, int end) {
        int half = (start + end) / 2;
        System.out.println("start = " + start + ", end = " + end + ", half = " + half + ", array[" + half + "] = " + array[half]);

        if(start == half) {
            return -1;
        }
        if(array[half] == number) {
            return half;
        } else if(array[half] > number) {
            return search(number, array, start, half);
        } else if (array[half] < number){
            return search(number, array, half, end);
        }

        return -1;
    }
}
