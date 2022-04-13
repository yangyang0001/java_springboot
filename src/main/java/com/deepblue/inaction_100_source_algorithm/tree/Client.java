package com.deepblue.inaction_100_source_algorithm.tree;

/**
 * 树节点的 先序遍历, 中序遍历, 后序遍历
 */
public class Client {

    public static void main(String[] args) {

        Node left1_left = new Node("left1_left", null, null, null);
        Node left1_righ = new Node("left1_righ", null, null, null);
        Node righ1_left = new Node("righ1_left", null, null, null);
        Node righ1_righ = new Node("righ1_righ", null, null, null);

        Node left1 = new Node("left1", null, null, null);
        Node righ1 = new Node("righ1", null, null, null);

        Node root = new Node("root", null, null, null);

        root.setLeft(left1);
        root.setRight(righ1);

        left1.setParent(root);
        righ1.setParent(root);

        left1.setLeft(left1_left);
        left1.setRight(left1_righ);
        righ1.setLeft(righ1_left);
        righ1.setRight(righ1_righ);

        left1_left.setParent(left1);
        left1_righ.setParent(left1);
        righ1_left.setParent(righ1);
        righ1_righ.setParent(righ1);

        System.out.println("先序遍历 :-------------------------------------------------------------");
        rootBegin(root);

        System.out.println("中序遍历 :-------------------------------------------------------------");
        rootEnd(root);

        System.out.println("后序遍历 :-------------------------------------------------------------");
        rootMid(root);

    }

    public static void rootBegin(Node root) {

        if(root != null) {
            System.out.println(root.getName());
        }

        if(root.getLeft() != null) {
            rootBegin(root.getLeft());
        }

        if(root.getRight() != null) {
            rootBegin(root.getRight());
        }

    }

    public static void rootEnd(Node root) {

        if(root.getLeft() != null) {
            rootEnd(root.getLeft());
        }

        if(root.getRight() != null) {
            rootEnd(root.getRight());
        }

        if(root.getLeft() == null && root.getLeft() == null) {
            System.out.println(root.getName());
        }

        if(root.getLeft() != null || root.getRight() != null) {
            System.out.println(root.getName());
        }

    }

    public static void rootMid(Node root) {

        if(root.getLeft() != null) {
            rootMid(root.getLeft());
        }

        if(root.getLeft() == null && root.getRight() == null) {
            System.out.println(root.getName());
        }

        if(root.getLeft() != null || root.getRight() != null) {
            System.out.println(root.getName());
        }

        if(root.getRight() != null) {
            rootMid(root.getRight());
        }

    }


}
