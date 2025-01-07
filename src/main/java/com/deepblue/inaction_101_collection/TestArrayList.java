package com.deepblue.inaction_101_collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 验证了 注释中的 一旦 ListIterator 创建后, 只能通过 iterator remove add 方法来进行修改, 其他线程同步修改发生异常 ConcurrentModificationException
 */
public class TestArrayList {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);

        ArrayList<String> list1 = new ArrayList<>();
        list1.add("zhangsan");

        Thread threadA = new Thread(() -> {
            /**
             * 验证情况1: ArrayList 在创建 ListIterator 后只能 通过 iterator remove add 修改, 否则抛出 ConcurrentModificationException
             */
//            ListIterator<String> iterator = list1.listIterator();
//            try {
//                latch.wait();
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            while(iterator.hasNext()) {
//                System.out.println(iterator.next());
//            }

            /**
             * 验证情况2: ArrayList 是非同步集合! 一旦同步修改ArrayList, 则会抛出 ConcurrentModificationException
             * list1.stream().forEach(System.out::println); 是不会出现这个问题的!
             */
            try {
                latch.await();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for(String str : list1) {
                System.out.println(str);
            }


        });

        Thread threadB = new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i = 0 ; ; i++) {
                list1.add("i=" + i);
            }
        });

        threadA.start();
        threadB.start();

        latch.countDown();

        // 同步 ArrayList 集合
        List list2 = Collections.synchronizedList(new ArrayList());
        list2.add("lisi");
        System.out.println("list2.size :" + list2.size());




    }


}
