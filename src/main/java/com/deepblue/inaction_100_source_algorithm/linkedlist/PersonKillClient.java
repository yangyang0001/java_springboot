package com.deepblue.inaction_100_source_algorithm.linkedlist;

import com.alibaba.fastjson.JSON;
import com.deepblue.inaction_00_spring.chapter_07.example_001_annotation.Mine;

/**
 *
 */
public class PersonKillClient {

    public static void main(String[] args) {

        MineNode head = new MineNode(1, null);
        MineNode tail = head;

        for(int i = 2 ; i <= 100; i++) {
            tail.setNext(new MineNode(i, null));
            tail = tail.getNext();
        }

        System.out.println(JSON.toJSONString(head));

        while (head != null) {

        }

    }
}
