package com.deepblue.inaction_04_redis_deep_adventure.chapter_01;

import com.deepblue.inaction_01_springboot2.chapter_12.util.BinaryUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 *
 */
public class TestObject {

    public static void main(String[] args) {

        long value = Long.MAX_VALUE;
        System.out.println(value);

        long first = 0B0011;
        System.out.println(first);

        String strh = BinaryUtil.getBinaryString("h");
        String stre = BinaryUtil.getBinaryString("e");
        String strl = BinaryUtil.getBinaryString("l");
        String str0 = BinaryUtil.getBinaryString("o");

        System.out.println(strh);
        System.out.println(stre);
        System.out.println(strl);
        System.out.println(str0);
        //   01234567
        // 0B01101000

        //   89ABCDEF
        // 0B01100101

        //   01234567
        // 0B01101100

        //   01234567
        // 0B01101111

        Random random = new Random();
        random.nextInt(100);

        System.out.println(143192);
        System.out.println(143192 / 1024);
        System.out.println(50 * 1024);
        System.out.println(100 * 1024);
        System.out.println(-157448);
        System.out.println(-100*1024);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(zero));
        System.out.println(zero.getTime());
        System.out.println(System.currentTimeMillis());


        System.out.println(16384/4);
        System.out.println();


    }
}
