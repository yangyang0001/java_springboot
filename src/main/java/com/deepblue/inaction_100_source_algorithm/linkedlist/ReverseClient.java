package com.deepblue.inaction_100_source_algorithm.linkedlist;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 *
 */
public class ReverseClient {

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


        Node reverse = reverse(node1);
        System.out.println("reverse = " + JSON.toJSONString(reverse));
    }

    public static Node reverse(Node head) {
        ArrayList<Node> stack = Lists.newArrayList();

        Node tail = head;

        while(tail != null) {
            stack.add(tail);
            tail = tail.getNext();
        }

        for(int i = 0; i < stack.size(); i++) {
            if(i == 0) {
                stack.get(i).setNext(null);
            } else {
                stack.get(i).setNext(stack.get(i-1));
            }
        }

        return stack.get(stack.size() - 1);
    }
}
