package com.deepblue.inaction_100_source_algorithm.linkedlist;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Client_001_ReverseNode {

    public static void main(String[] args) {

        Node node5 = new Node("node5", null);
        Node node4 = new Node("node4", node5);
        Node node3 = new Node("node3", node4);
        Node node2 = new Node("node2", node3);
        Node node1 = new Node("node1", node2);

        Node reverse = reverse(node1);

        while(reverse != null) {
            System.out.println(reverse.getName());
            reverse = reverse.getNext();
        }

    }

    public static Node reverse(Node node) {

        List<Node> list = new ArrayList<Node>();

        while(node != null) {
            list.add(node);
            node = node.getNext();
        }

        for(int i = 0; i < list.size(); i++) {
            if(i == 0) {
                list.get(i).setNext(null);
            } else {
                list.get(i).setNext(list.get(i-1));
            }
        }

        return list.get(list.size() - 1);
    }


}
