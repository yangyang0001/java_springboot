package com.deepblue.inaction_100_source_algorithm.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 */
public class MergeClient {

    public static void main(String[] args) {

        Node node1 = new Node("1", null);
        Node node2 = new Node("2", null);
        Node node3 = new Node("3", null);
        node1.setNext(node2);
        node2.setNext(node3);

        Node node4 = new Node("4", null);
        Node node5 = new Node("5", null);
        node4.setNext(node5);

        Node node6 = new Node("6", null);
        Node node7 = new Node("7", null);
        node6.setNext(node7);


        Node[] nodes_1 = new Node[] {node1, node2, node3};
        Node[] nodes_2 = new Node[] {node4, node5};
        Node[] nodes_3 = new Node[] {node6, node7};

        Node head = getHead(nodes_1, nodes_2, nodes_3);
        System.out.println(head);

        while(head != null) {
            System.out.println(head.getName());
            head = head.getNext();
        }

    }

    public static Node getHead(Node[] ... nodeList) {

        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        for(Node[] nodes : nodeList) {
            for(Node node : nodes) {
                queue.add(node);
            }
        }

        Node head = new Node();
        Node tail = head;

        while(!queue.isEmpty()) {
            Node temp = queue.poll();
            tail.setNext(temp);
            tail = tail.getNext();
        }

        return head.getNext();
    }
}
