package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_034;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 */
public class Node {

    private int coin;

    public Node(int coin) {
        this.coin = coin;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Node node = (Node) o;
        return coin == node.coin;
    }

    public static void main(String[] args) {

        Node node1 = new Node(1);
        Node node2 = new Node(1);
        Node node3 = new Node(1);
        Node node4 = new Node(1);

        List<Node> nodes = new ArrayList<>();

        if(!nodes.contains(node1)) {
            nodes.add(node1);
        }
        if(!nodes.contains(node2)) {
            nodes.add(node2);
        }
        if(!nodes.contains(node3)) {
            nodes.add(node3);
        }
        if(!nodes.contains(node4)) {
            nodes.add(node4);
        }

        System.out.println(node1.equals(node2));
        System.out.println(JSON.toJSONString(nodes));

    }

}
