package com.deepblue.inaction_100_source_algorithm_back.huawei.test_009.daoyu;

/**
 *
 */
public class Main {

    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length; //记录行列
        int res = 0;
        //遍历表格，挨着的岛屿为整个岛屿，所以记录完岛屿个数继续调用 DFS 进一步覆盖相邻岛屿
        for(int i =0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    res++;

                    dfs(grid,i,j);
                }

            }
        }
        return res;
    }

    public void dfs(char[][]grid, int i, int j){

        int m = grid.length, n = grid[0].length;

        if(i < 0 || j < 0 || i >= m || j >= n) return; //超出边界

        if (grid[i][j] == '0') {
            // 已经是海水了
            return;
        }

        // 如果是陆地，就淹没陆地。（此时已经记录过这个单元格了）
        grid[i][j] = '0';

        // 淹没上下左右的陆地
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);

    }
}


