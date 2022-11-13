package com.deepblue.inaction_100_source_algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TopBegin {

    public static void main(String[] args) {

        Node root  = new Node("root", null, null, null);

        Node left1 = new Node("left1", root, null, null);
        Node righ1 = new Node("righ1", root, null, null);

//        Node left1_left = new Node("left1_left", left1, null, null);
//        Node left1_righ = new Node("left1_righ", left1, null, null);

        Node righ1_left = new Node("righ1_left", righ1, null, null);
        Node righ1_righ = new Node("righ1_righ", righ1, null, null);

        root.setLeft(left1);
        root.setRight(righ1);

//        left1.setLeft(left1_left);
//        left1.setRight(left1_righ);

        righ1.setLeft(righ1_left);
        righ1.setRight(righ1_righ);

        List<Node> rootList = new ArrayList<Node>();
        rootList.add(root);

        topBottom(rootList);

    }

    public static void topBottom(List<Node> rootList) {

        if(rootList.isEmpty()) {
            return;
        }

        List<Node> children = new ArrayList<>();
        for(Node node : rootList) {
            System.out.println(node.getName());

            if(node.getLeft() != null) {
                children.add(node.getLeft());
            }
            
            if(node.getRight() != null) {
                children.add(node.getRight());
            }
        }

        topBottom(children);
    }

}
