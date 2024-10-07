package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_025;

import com.alibaba.fastjson.JSON;
import com.deepblue.inaction_100_source_algorithm_back.huawei.test_008.printtree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(0);

        TreeNode left = new TreeNode(1);
        TreeNode righ = new TreeNode(2);

        TreeNode left_left = new TreeNode(11);
        TreeNode left_righ = new TreeNode(12);

        TreeNode righ_left = new TreeNode(21);
        TreeNode righ_righ = new TreeNode(22);

        root.left  = left;
        root.right = righ;

        left.left  = left_left;
        left.right = left_righ;

        righ.left  = righ_left;
        righ.right = righ_righ;

        System.out.println("RootBegin method invoke start -----------------------------------");
        List<Integer> rootBegin = rootBegin(root, new ArrayList<>());
        int[] rootBegins = rootBegin.stream().mapToInt(item -> Integer.valueOf(item)).toArray();
        System.out.println(JSON.toJSONString(rootBegins));


        System.out.println("RootMidd method invoke start ------------------------------------");
        List<Integer> rootMidd = rootMidd(root, new ArrayList<>());
        int[] rootMidds = rootMidd.stream().mapToInt(item -> Integer.valueOf(item)).toArray();
        System.out.println(JSON.toJSONString(rootMidds));


        System.out.println("RootLast method invoke start -----------------------------------");
        List<Integer> rootLast = rootLast(root, new ArrayList<>());
        int[] rootLasts = rootLast.stream().mapToInt(item -> Integer.valueOf(item)).toArray();
        System.out.println(JSON.toJSONString(rootLasts));


        System.out.println("TopBegin method invoke start -----------------------------------");
        List<TreeNode> rootList = new ArrayList<>();
        rootList.add(root);
        List<Integer> topBegin = topBegin(rootList, new ArrayList<>());
        int[] topBegins = topBegin.stream().mapToInt(item -> Integer.valueOf(item)).toArray();
        System.out.println(JSON.toJSONString(topBegins));

    }


    public static List<Integer> rootBegin(TreeNode root, List<Integer> list) {
        if(root != null) {
            list.add(root.val);
        }

        if(root.left != null) {
            list = rootBegin(root.left, list);
        }

        if(root.right != null) {
            list = rootBegin(root.right, list);
        }

        return list;
    }

    public static List<Integer> rootMidd(TreeNode root, List<Integer> list) {

        if(root!= null && root.left != null) {
            list = rootMidd(root.left, list);
        }

        if(root != null) {
            list.add(root.val);
        }

        if(root!= null && root.right != null) {
            list = rootMidd(root.right, list);
        }

        return list;
    }

    public static List<Integer> rootLast(TreeNode root, List<Integer> list) {
        if(root!= null && root.left != null) {
            list = rootLast(root.left, list);
        }

        if(root!= null && root.right != null) {
            list = rootLast(root.right, list);
        }

        if(root != null) {
            list.add(root.val);
        }

        return list;
    }


    public static List<Integer> topBegin(List<TreeNode> rootList, List<Integer> list) {

        if(rootList.isEmpty()) {
            return list;
        }

        if (!rootList.isEmpty()) {
            list.addAll(rootList.stream().map(item -> item.val).collect(Collectors.toList()));
        }

        List<TreeNode> children = new ArrayList<>();
        for (TreeNode root : rootList) {
            if(root != null && root.left != null) {
                children.add(root.left);
            }

            if(root != null && root.right != null) {
                children.add(root.right);
            }
        }

        list = topBegin(children, list);

        return list;
    }


}
