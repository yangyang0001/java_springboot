package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_034;

import com.alibaba.fastjson.JSON;
import sun.awt.IconInfo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * (10) leetcode 322. 零钱兑换 ， 也可搜索 动态规划相关题型
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {

            String[] arr = scanner.nextLine().split(" ");
            int[] nums = Arrays.stream(arr).mapToInt(item -> Integer.valueOf(item)).toArray();

            int amount = Integer.valueOf(scanner.nextLine());

            int min = coinChange(nums, amount);
            System.out.println(min);
        }

    }

    public static int coinChange(int[] nums, int amount) {
        if (amount == 0) {
            return 0;
        }

        List<Node> root = new ArrayList<>();
        root.add(new Node(amount));
        for (int count = 1; count < Integer.MAX_VALUE; count++) {
            root = execute(nums, root, amount);

            if (root == null || root.isEmpty()) {
                return -1;
            }

            long zero = root.stream().filter(item -> item.getCoin() == 0).count();
            if (zero > 0) {
                return count;
            }

            root = root.stream().filter(item -> item.getCoin() >= 0).collect(Collectors.toList());
        }

        return -1;
    }

    /**
     * 1 2 5
     * 11
     * <p>
     * 2 4 6 8 10 12 14 16 18 20 22 24
     * 9999
     */
    public static List<Node> execute(int[] nums, List<Node> root, int count) {
        List<Node> children = new ArrayList<>();
        for (Node node : root) {
            int coin = node.getCoin();
            for (int i = 0; i < nums.length; i++) {
                int left = coin - nums[i];
                Node child = new Node(left);
                if (!children.contains(child)) {
                    children.add(child);
                }
            }
        }
        return children;
    }

}