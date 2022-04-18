package com.deepblue.inaction_100_source_algorithm.linkedlist;

import lombok.*;

/**
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Node {

    private String name;

    private Node next;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
