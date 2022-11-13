package com.deepblue.inaction_100_source_algorithm_back.input;

import org.apache.logging.log4j.util.Strings;

import java.io.*;


/**
 *
 */
public class Main {

    public static void main(String[] args) {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(System.in));

            while(true) {
                String line = reader.readLine();
                if(Strings.isBlank(line)) {
                    return;
                } else {
                    String[] params = line.split(" ");
                    int a = Integer.valueOf(params[0]);
                    int b = Integer.valueOf(params[0]);
                    System.out.println(count(a, b));

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static int count(int a, int b) {
        return a + b;
    }


}
