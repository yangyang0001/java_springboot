package com.deepblue.inaction_100_source_algorithm_back.huawei.test_010;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();

        SimpleDateFormat defaultSDF  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(defaultSDF.format(zero));
        System.out.println(defaultSDF.format(new Date()));
    }

}

