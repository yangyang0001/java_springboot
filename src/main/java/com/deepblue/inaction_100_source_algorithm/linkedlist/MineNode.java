package com.deepblue.inaction_100_source_algorithm.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MineNode {

    private Integer value;

    private MineNode next;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public MineNode getNext() {
        return next;
    }

    public void setNext(MineNode next) {
        this.next = next;
    }
}
