package com.deepblue.inaction_100_source_algorithm.linkedlist;

import com.alibaba.fastjson.JSON;

/**
 * 快慢指针查找 链表的中点!
 */
public class MiddleSearchClient {

    public static void main(String[] args) {

        Node node1 = new Node("node1", null);
        Node node2 = new Node("node2", null);
        Node node3 = new Node("node3", null);
        Node node4 = new Node("node4", null);
        Node node5 = new Node("node5", null);
        Node node6 = new Node("node6", null);
        Node node7 = new Node("node7", null);
        Node node8 = new Node("node8", null);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        node7.setNext(node8);

        Node midNode = getMidNode(node1);
        System.out.println(JSON.toJSONString(midNode));

    }

    public static Node getMidNode(Node head) {
        Node slow = head;
        Node fast = head;

        while(fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        return slow;
    }
}
