package com.deepblue.inaction_103_mine.test_001;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 * <p>
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 * <p>
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 * <p>
 * 返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动 ，既可流向太平洋也可流向大西洋 。
 * <p>
 * 实例1：
 * 输入: heights = [
 * [1,2,2,3,5],
 * [3,2,3,4,4],
 * [2,4,5,3,1],
 * [6,7,1,4,5],
 * [5,1,1,2,4]
 * ]
 * 输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * <p>
 * 示例 2：
 * 输入: heights = [
 * 太
 * 太 [2,1], 大
 * [1,2]
 * 大
 * ]
 * 输出: [[0,0],[0,1],[1,0],[1,1]]
 */
public class Main {

    public static void main(String[] args) {
        int[][] heights = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
//        int[][] heights = {
//                {2, 1},
//                {1, 2}
//        };
//        List<List<Integer>> result = pacificAtlantic(heights);
//        System.out.println(JSON.toJSONString(result));
        List<String> execute = execute(heights);
        System.out.println(JSON.toJSONString(execute));

    }

//    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
//        List<List<Integer>> res = new ArrayList();
//        int m = heights.length;
//        int n = heights[0].length;
//
//        // 记录坐标地是否到达太平洋、大西洋
//        boolean canReachP[][] = new boolean[m][n];
//        boolean canReachX[][] = new boolean[m][n];
//
//        // 上下左右出发，深度优先搜索
//        for (int i = 0; i < m; i++) {
//            dfs(heights, canReachP, i, 0);
//            dfs(heights, canReachX, i, n - 1);
//        }
//
//        for (int j = 0; j < n; j++) {
//            dfs(heights, canReachP, 0, j);
//            dfs(heights, canReachX, m - 1, j);
//        }
//
//        //遍历记录，如果都可到达即可加入结果
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (canReachP[i][j] && canReachX[i][j]) {
//                    res.add(Arrays.asList(i, j));
//                }
//            }
//        }
//
//        return res;
//    }
//
//    public static void dfs(int[][] heights, boolean[][] canReach, int i, int j) {
//
//        // 如果已经扫描过可达就不用扫描
//        if (canReach[i][j]) return;
//
//        // 扫描过即说明可达，这也是逆流的优点
//        canReach[i][j] = true;
//
//        // 上下左右深度搜索，越界就不搜索
//        if (i - 1 >= 0 && heights[i - 1][j] >= heights[i][j]) {
//            dfs(heights, canReach, i - 1, j);
//        }
//
//        if (j - 1 >= 0 && heights[i][j - 1] >= heights[i][j]) {
//            dfs(heights, canReach, i, j - 1);
//        }
//
//        if (i + 1 < heights.length && heights[i + 1][j] >= heights[i][j]) {
//            dfs(heights, canReach, i + 1, j);
//        }
//
//        if (j + 1 < heights[0].length && heights[i][j + 1] >= heights[i][j]) {
//            dfs(heights, canReach, i, j + 1);
//        }
//
//    }


    public static List<String> execute(int[][] params) {

        List<String> result = new ArrayList<>();

        // 行 和 列
        int m = params.length;
        int n = params[0].length;

        boolean[][] canReachP = new boolean[m][n];
        boolean[][] canReachX = new boolean[m][n];


        for (int i = 0; i < m; i++) {
            dfs(params, canReachP, i, 0);
            dfs(params, canReachX, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            dfs(params, canReachP, 0, j);
            dfs(params, canReachX, m - 1, j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canReachP[i][j] && canReachX[i][j]) {
                    result.add("[" + i + "," + j + "]");
                }
            }
        }

        return result;

    }

    public static void dfs(int[][] params, boolean[][] canReach, int i, int j) {

        if (canReach[i][j]) {
            return;
        }

        canReach[i][j] = true;

        if (i - 1 >= 0 && params[i][j] <= params[i - 1][j]) {
            dfs(params, canReach, i - 1, j);
        }

        if (i + 1 < params.length && params[i][j] <= params[i + 1][j]) {
            dfs(params, canReach, i + 1, j);
        }

        if (j - 1 >= 0 && params[i][j] <= params[i][j - 1]) {
            dfs(params, canReach, i, j - 1);
        }

        if (j + 1 < params[0].length && params[i][j] <= params[i][j + 1]) {
            dfs(params, canReach, i, j + 1);
        }

    }

}


