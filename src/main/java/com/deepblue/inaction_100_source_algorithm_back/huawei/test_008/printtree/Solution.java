package com.deepblue.inaction_100_source_algorithm_back.huawei.test_008.printtree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Data
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class Solution {

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> valueList = new ArrayList<>();
        if(root == null) {
            return valueList;
        }

        ArrayList<TreeNode> rootList = new ArrayList<TreeNode>();
        rootList.add(root);

        return topBottom(rootList, valueList);
    }

    public ArrayList<Integer> topBottom(ArrayList<TreeNode> rootList, ArrayList<Integer> valueList) {

        if (rootList.isEmpty()) {
            return new ArrayList<Integer>();
        }

        ArrayList<TreeNode> children = new ArrayList<>();
        for (TreeNode node : rootList) {
            valueList.add(node.val);

            if (node.left != null) {
                children.add(node.left);
            }
            if (node.right != null) {
                children.add(node.right);
            }
        }

        topBottom(children, valueList);

        return valueList;
    }
}