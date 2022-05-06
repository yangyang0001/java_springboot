package com.deepblue.inaction_100_source_algorithm.search;

/**
 *
 */
public class BinarySearch {

    public static void main(String[] args) {

        int[] arr = {1, 3, 5, 7, 9, 11};

        int number = 11;
        int index = search(arr, 0, arr.length, number);

        if (index < 0) {
            System.out.println("result index = " + index);
        } else {
            System.out.println("result index = " + index + ", arr[" + index + "] = " + arr[index]);
        }

    }

    public static int search(int arr[], int start, int end, int number) {

        int middle = (start + end) / 2;
        int index = middle;

        System.out.println("method invoke, start = " + start + ", end = " + end + ", middle = " + middle + ", index = " + index + ", arr[" + index + "] = " + arr[index]);
        if(arr[middle] == number) {
            return index;
        }

        if(start == middle) {
            return -1;
        }

        if(arr[middle] > number) {
            return search(arr, start, middle, number);
        }

        if(arr[middle] < number) {
            return search(arr, middle, end, number);
        }

        return index;
    }
}
