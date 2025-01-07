package com.deepblue.inaction_105_icu4j;

import com.ibm.icu.text.Transliterator;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        String source = "ヨウケンイ";

        Transliterator trH2F = Transliterator.getInstance("Halfwidth-Fullwidth");

        System.out.println(source + "→★(半角文字→全角文字)★→" + trH2F.transliterate(source));



    }
}
