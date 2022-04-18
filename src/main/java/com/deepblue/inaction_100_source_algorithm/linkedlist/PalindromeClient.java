package com.deepblue.inaction_100_source_algorithm.linkedlist;

import com.alibaba.fastjson.JSON;
import org.bouncycastle.util.Arrays;

/**
 *
 */
public class PalindromeClient {

    public static void main(String[] args) {

        Node node1 = new Node("A", null);
        Node node2 = new Node("B", null);
        Node node3 = new Node("C", null);
        Node node4 = new Node("A", null);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);

        StringBuilder source = new StringBuilder(source(node1, ""));

        String sourcestr = source.toString();
        String revresstr = source.reverse().toString();

        System.out.println("sourcestr = " + sourcestr + ", revresstr = " + revresstr);

        if(sourcestr.equals(revresstr.toString())) {
            System.out.println("是回环字符串");
        } else {
            System.out.println("不是回环字符串");
        }

    }

    public static String source(Node node, String source) {

        if(node != null) {
            source += node.getName();
        }
        System.out.println("node is : " + JSON.toJSONString(node) + ", source = " + source);

        if(node.getNext() != null) {
            source = source(node.getNext(), source);
        }

        return source;
    }

}
