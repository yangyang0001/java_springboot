package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_036;

/**
 * (12) 岛屿问题 leetcode 200
 */
public class Main {


    public static void main(String[] args) {

        char[][] grid = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };

        int i = numIslands(grid);
        System.out.println(i);
    }


    public static int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == '1') {
                    dfs(grid,  i,  j);
                    count ++;
                }
            }
        }
        return count;
    }

    public static void dfs(char[][] grid,  int i,  int j) {

        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '#';

        dfs(grid,  i - 1,  j);
        dfs(grid,  i,  j + 1);
        dfs(grid,  i + 1,  j);
        dfs(grid,  i,  j - 1);
    }
}
