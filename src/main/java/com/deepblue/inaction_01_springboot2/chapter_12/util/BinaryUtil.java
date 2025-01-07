package com.deepblue.inaction_01_springboot2.chapter_12.util;

/**
 *
 */
public class BinaryUtil {

    public static String getBinaryString(String source) {
        StringBuffer buffer = new StringBuffer("");

        char[] array = source.toCharArray();
        for ( int i = 0; i < array.length; i++ ) {
            String binStr = Integer.toBinaryString(array[i]);
            buffer.append(binStr);
        }

        return buffer.toString();
    }
}
