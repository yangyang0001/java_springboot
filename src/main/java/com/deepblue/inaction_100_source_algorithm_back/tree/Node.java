package com.deepblue.inaction_100_source_algorithm_back.tree;

import lombok.*;

/**
 *
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

