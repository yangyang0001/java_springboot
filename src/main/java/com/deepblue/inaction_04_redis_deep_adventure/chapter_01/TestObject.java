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

//        long value = Long.MAX_VALUE;
//        System.out.println(value);
//
//        long first = 0B0011;
//        System.out.println(first);
//
//        String strh = BinaryUtil.getBinaryString("h");
//        String stre = BinaryUtil.getBinaryString("e");
//        String strl = BinaryUtil.getBinaryString("l");
//        String str0 = BinaryUtil.getBinaryString("o");
//
//        System.out.println(strh);
//        System.out.println(stre);
//        System.out.println(strl);
//        System.out.println(str0);
//        //   01234567
//        // 0B01101000
//
//        //   89ABCDEF
//        // 0B01100101
//
//        //   01234567
//        // 0B01101100
//
//        //   01234567
//        // 0B01101111
//
//        Random random = new Random();
//        random.nextInt(100);
//
//        System.out.println(143192);
//        System.out.println(143192 / 1024);
//        System.out.println(50 * 1024);
//        System.out.println(100 * 1024);
//        System.out.println(-157448);
//        System.out.println(-100*1024);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        Date zero = calendar.getTime();
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(zero));
//        System.out.println(zero.getTime());
//        System.out.println(System.currentTimeMillis());
//
//
//        System.out.println(16384/4);
//
//        long bits   = 4000000000L;      // bits
//        long bytes  = bits   / 8;       // Byte
//        long kbytes = bytes  / 1024;    // KB
//        long mbytes = kbytes / 1024;    // MB
//        System.out.println("bits   = " + bits  );
//        System.out.println("bytes  = " + bytes );
//        System.out.println("kbytes = " + kbytes);
//        System.out.println("mbytes = " + mbytes);



//        String a0 = "B", a1 = "Y";
//        String b0 = "b", b1 = "y";
//        String c0 = "1", c1 = "8";
//        String d0 = "";
//
//        System.out.println(a0.compareTo("A") >= 0 && a0.compareTo("Z") <=0);
//        System.out.println(a1.compareTo("A") >= 0 && a1.compareTo("Z") <=0);
//
//        System.out.println(b0.compareTo("a") >= 0 && b0.compareTo("z") <=0);
//        System.out.println(b1.compareTo("a") >= 0 && b1.compareTo("z") <=0);
//
//        System.out.println(c0.compareTo("0") >= 0 && c0.compareTo("9") <=0);
//        System.out.println(c1.compareTo("0") >= 0 && c1.compareTo("9") <=0);

//        String a = "0100";
//
//        System.out.println(Long.valueOf(a, 2));


//        StringBuffer buffer = new StringBuffer("");
//        buffer.append("abc").append("def");
//
//        System.out.println(buffer.toString());
//        System.out.println(buffer.reverse().toString());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date zero = calendar.getTime();

        SimpleDateFormat defaultSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println("time = " + defaultSDF.format(zero) + ", time = " + zero.getTime());





    }
}
