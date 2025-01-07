package com.deepblue.inaction_100_source_algorithm_back.huawei_01.test_029;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        int[][] grid = new int[][] {
                {2, 1, 1},
                {0, 1, 1},
                {0, 1, 1},
                {1, 0, 1},



//                {2, 1, 1},
//                {0, 1, 2},
//                {1, 0, 1},
//                {1, 0, 1}

//                {2, 2, 2},
//                {0, 2, 2},
//                {1, 0, 2},
//                {1, 0, 1}

//                {2, 2, 2},
//                {0, 2, 2},
//                {1, 0, 2},
//                {1, 0, 2}
        };


        int i = orangesRotting(grid);
        System.out.println("minutes = " + i);

    }

    public static int orangesRotting(int[][] grid) {

        String s = Arrays.deepToString(grid);
        if(!s.contains("1")) {
            return 0;
        }
        if(!s.contains("2")) {
            return -1;
        }

        for (int i = 0; i <= grid.length * grid[0].length; i++) {
            String before = Arrays.deepToString(grid);
            System.out.println("i = " + i + ", grid = " + before);

            grid = compute(grid);

            String after = Arrays.deepToString(grid);
            if (after.equals(before)) {
                if(after.contains("1")) {
                    return -1;
                }
                if(!after.contains("1")) {
                    return i;
                }
            }
        }

        return -1;
    }

    public static int[][] compute(int[][] grid) {
        List<String> changes = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
//                boolean addrow = false;
//                boolean subrow = false;
//                boolean addcol = false;
//                boolean subcol = false;
//
//                if(grid[i][j] == 1) {
//                    System.out.println("grid[" + i + "][" + j + "] = " + grid[i][j]);
//                    if(i == 0 && (i+1) < grid.length) {
//                        if(grid[i+1][j] == 0){
//                            addrow = true;
//                        }
//                    } else if(i == grid.length-1 && (i-1) >= 0) {
//                        if(grid[i-1][j] == 0) {
//                            subrow = true;
//                        }
//                    } else if((i+1) < grid.length && (i-1) >= 0){
//                        if(grid[i+1][j] == 0) {
//                            addrow = true;
//                        }
//                        if(grid[i-1][j] == 0) {
//                            subrow = true;
//                        }
//                    } else {
//                        addrow = true;
//                        subrow = true;
//                    }
//
//                    if(j == 0 && (j+1) < grid[0].length) {
//                        if(grid[i][j+1] == 0) {
//                            addcol = true;
//                        }
//                    } else if(j == grid[0].length-1 && (j-1) >= 0) {
//                        if(grid[i][j-1] == 0) {
//                            subcol = true;
//                        }
//                    } else if ((j+1) < grid[0].length && (j-1) >= 0){
//                        if(grid[i][j+1] == 0) {
//                            addcol = true;
//                        }
//                        if(grid[i][j-1] == 0) {
//                            subcol = true;
//                        }
//                    } else {
//                        addcol = true;
//                        subcol = true;
//                    }
//                }
//
//                if(addrow && subrow && addcol && subcol) {
//                    return null;
//                }

                if(grid[i][j] == 2) {
                    StringBuffer exist = new StringBuffer("");
                    exist.append("[").append(i).append("][").append(j).append("]");
                    if(changes.contains(exist.toString())) {
                        continue;
                    }

                    StringBuffer buffer = null;
                    if(i == 0 && (i+1) < grid.length) {
                        if(grid[i+1][j] == 1){
                            grid[i+1][j] = 2;
                            buffer = new StringBuffer("");
                            buffer.append("[").append(i+1).append("][").append(j).append("]");
                            changes.add(buffer.toString());
                        }
                    } else if(i == grid.length-1 && (i-1) >= 0) {
                        if(grid[i-1][j] == 1) {
                            grid[i-1][j] = 2;
                            buffer = new StringBuffer("");
                            buffer.append("[").append(i-1).append("][").append(j).append("]");
                            changes.add(buffer.toString());
                        }
                    } else if((i+1) < grid.length && (i-1) >= 0){
                        if(grid[i+1][j] == 1) {
                            grid[i+1][j] = 2;
                            buffer = new StringBuffer("");
                            buffer.append("[").append(i+1).append("][").append(j).append("]");
                            changes.add(buffer.toString());
                        }
                        if(grid[i-1][j] == 1) {
                            grid[i-1][j] = 2;
                            buffer = new StringBuffer("");
                            buffer.append("[").append(i-1).append("][").append(j).append("]");
                            changes.add(buffer.toString());
                        }
                    }

                    if(j == 0 && (j+1) < grid[0].length) {
                        if(grid[i][j+1] == 1) {
                            grid[i][j+1] = 2;
                            buffer = new StringBuffer("");
                            buffer.append("[").append(i).append("][").append(j+1).append("]");
                            changes.add(buffer.toString());
                        }
                    } else if(j == grid[0].length-1 && (j-1) >= 0) {
                        if(grid[i][j-1] == 1) {
                            grid[i][j-1] = 2;
                            buffer = new StringBuffer("");
                            buffer.append("[").append(i).append("][").append(j-1).append("]");
                            changes.add(buffer.toString());
                        }
                    } else if ((j+1) < grid[0].length && (j-1) >= 0){
                        if(grid[i][j+1] == 1) {
                            grid[i][j+1] = 2;
                            buffer = new StringBuffer("");
                            buffer.append("[").append(i).append("][").append(j+1).append("]");
                            changes.add(buffer.toString());
                        }
                        if(grid[i][j-1] == 1) {
                            grid[i][j-1] = 2;
                            buffer = new StringBuffer("");
                            buffer.append("[").append(i).append("][").append(j-1).append("]");
                            changes.add(buffer.toString());
                        }
                    }
                }
            }
        }

//        System.out.println("changes = " + JSON.toJSONString(changes));
        return grid;
    }
}
