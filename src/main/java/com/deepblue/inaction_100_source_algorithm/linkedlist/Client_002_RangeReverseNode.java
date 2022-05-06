package com.deepblue.inaction_100_source_algorithm.linkedlist;

/**
 *
 */
public class Client_002_RangeReverseNode {


    public synchronized static void main(String[] args) throws Exception{

        Node node5 = new Node("node5", null);
        Node node4 = new Node("node4", node5);
        Node node3 = new Node("node3", node4);
        Node node2 = new Node("node2", node3);
        Node node1 = new Node("node1", node2);


        reverse(node1, 1, 5);

        Node node = node1;

        while(node != null) {
            System.out.println(node.getName());
            node = node.getNext();
        }
    }

    public static void reverse(Node node, int start, int end) throws InterruptedException {

        Node temp = new Node("NULL", node);
        Node pre = temp;
        for(int i = 0; i < start - 1; i++){
            pre = pre.getNext();
        }

        System.out.println("pre name = " + pre.getName());

        Node curr = pre.getNext();
        Node next;
        for(int i = 0; i < end - start; i++){
            next = curr.getNext();
            curr.setNext(next.getNext());
            next.setNext(pre.getNext());
            pre.setNext(next);
        }

    }



}
