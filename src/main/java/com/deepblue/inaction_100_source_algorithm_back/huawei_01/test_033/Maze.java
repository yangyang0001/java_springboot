package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_033;

import lombok.*;

/**
 *
 */

public class Maze {

    private int i;

    private int j;

    private Maze prev;

    private Maze next;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public Maze getPrev() {
        return prev;
    }

    public void setPrev(Maze prev) {
        this.prev = prev;
    }

    public Maze getNext() {
        return next;
    }

    public void setNext(Maze next) {
        this.next = next;
    }
}
