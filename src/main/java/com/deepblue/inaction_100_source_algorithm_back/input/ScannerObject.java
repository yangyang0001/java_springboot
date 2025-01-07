package com.deepblue.inaction_100_source_algorithm_back.input;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 */
public class ScannerObject {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(a + b);
        }

        while(scanner.hasNextLine()) {

            String line = scanner.nextLine();
            String[] params = line.split(",");

            Arrays.sort(params, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });

            for(int i = 0; i < params.length; i++) {
                String output = i == params.length - 1 ? params[i] : params[i] + ",";
                System.out.print(output);
            }
        }

    }

}
