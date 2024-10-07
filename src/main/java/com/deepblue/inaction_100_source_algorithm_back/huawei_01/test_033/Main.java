package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_033;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 5 5
 * 0 1 0 0 0
 * 0 1 1 1 0
 * 0 0 0 0 0
 * 0 1 1 1 0
 * 0 0 0 1 0
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int rows = Integer.valueOf(line.split(" ")[0]);
            int cols = Integer.valueOf(line.split(" ")[1]);
            int[][] nums = new int[rows][cols];

            for (int i = 0; i < rows; i++) {
                String[] split = scanner.nextLine().split(" ");
                for (int j = 0; j < cols; j++) {
                    nums[i][j] = Integer.valueOf(split[j]);
                }
            }

            Maze root = new Maze();
            root.setI(0);
            root.setJ(0);

            root = maze(root, nums);
            List<String> list = new ArrayList<>();
            while(root != null) {
                list.add("(" + root.getI() + "," + root.getJ() + ")");
                root = root.getPrev();
            }

            for (int i = list.size() - 1; i >= 0; i--) {
                System.out.println(list.get(i));
            };

        }
    }

    /**
     * 8 8
     * 0 1 0 0 0 1 0 1
     * 0 1 0 1 1 1 0 0
     * 0 1 0 0 0 0 1 0
     * 0 1 0 0 1 0 0 0
     * 0 0 1 0 0 1 1 0
     * 0 1 1 0 0 0 1 0
     * 0 1 0 0 1 1 1 0
     * 0 0 0 1 0 1 1 0
     * <p>
     * 5 5
     * 0 1 0 0 0
     * 0 1 1 1 0
     * 0 0 0 0 0
     * 0 1 1 1 0
     * 0 0 0 1 0
     */
    public static Maze maze(Maze root, int[][] nums) throws InterruptedException {
//        System.out.println(root.getI() + "," + root.getJ());
        int i = root.getI();
        int j = root.getJ();
        Maze next = new Maze();
        int nextI = 0;
        int nextJ = 0;

        if(i == nums.length - 1 && j == nums[0].length - 1) {
            return root;
        }

        if (i - 1 >= 0 && nums[i - 1][j] == 0 && !visited(root, i - 1, j)) {
            // 上
            nextI = i - 1;
            nextJ = j;
        } else if (j + 1 < nums[0].length && nums[i][j + 1] == 0 && !visited(root, i, j + 1)) {
            // 右
            nextI = i;
            nextJ = j + 1;

        } else if (i + 1 < nums.length && nums[i + 1][j] == 0 && !visited(root, i + 1, j)) {
            // 下
            nextI = i + 1;
            nextJ = j;

        } else if (j - 1 >= 0 && nums[i][j - 1] == 0 && !visited(root, i, j - 1)) {
            // 左
            nextI = i;
            nextJ = j - 1;
        }

        if(nextI == 0 && nextJ == 0) {
             nums[i][j] = 1;
             root.getPrev().setNext(null);
             next = root.getPrev();
        } else {
            next.setI(nextI);
            next.setJ(nextJ);
            root.setNext(next);
            next.setPrev(root);
        }

        return maze(next, nums);
    }

    public static boolean visited(Maze root, int i, int j) {
        while (root != null) {
            if (root.getI() == i && root.getJ() == j) {
                return true;
            }
            root = root.getPrev();
        }
        return false;
    }


}
