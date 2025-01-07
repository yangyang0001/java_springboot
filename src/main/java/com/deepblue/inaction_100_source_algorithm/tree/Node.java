package com.deepblue.inaction_100_source_algorithm.tree;

import lombok.*;

/**
 * 树节点
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Node {

    private String name;

    private Node parent;

    private Node left;

    private Node right;

}
