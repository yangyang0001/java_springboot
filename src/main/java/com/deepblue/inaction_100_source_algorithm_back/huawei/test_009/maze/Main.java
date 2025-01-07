package com.deepblue.inaction_100_source_algorithm_back.huawei.test_009.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] split = scanner.nextLine().split(" ");
        int xlength = Integer.parseInt(split[0]);
        int ylength = Integer.parseInt(split[1]);

        int[][] array = new int[xlength][ylength];

        for (int i = 0; i < xlength; i++) {
            String line = scanner.nextLine();
            String[] numbers = line.split(" ");
            for (int j = 0; j < ylength; j++) {
                array[i][j] = Integer.parseInt(numbers[j]);
            }
        }



//        int[][] array = {
//                {0, 1, 0, 0, 0},
//                {0, 1, 1, 1, 0},
//                {0, 0, 0, 0, 0},
//                {0, 1, 1, 1, 0},
//                {0, 0, 0, 1, 0}
//        };
//
//        for(int i = 0; i < array.length; i++) {
//            for(int j = 0; j < array[0].length; j++) {
//                System.out.print(array[i][j] + " ");
//            }
//            System.out.println();
//        }

        List<String> result = new ArrayList<>();

        boolean flag = boot(array, 0, 0, result);
        result.stream().forEach(System.out::println);

    }

    public static boolean boot(int[][] array, int x, int y, List<String> result) {
        int xMax = array.length;
        int yMax = array[0].length;

//        Thread.sleep(1000L);

        result.add("(" + x + "," + y + ")");
//        System.out.println("current:(" + x + "," + y + ")" + ", " + JSON.toJSONString(result));

        array[x][y] = 1;

//        for(int i = 0; i < array.length; i++) {
//            for(int j = 0; j < array[0].length; j++) {
//                System.out.print(array[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("-----------------------------------------------------");


        // 到达终点
        if(x == xMax - 1 && y == yMax - 1) {
            return true;
        }

        // 下
        if(x + 1 < xMax && array[x+1][y] == 0) {
            if(boot(array, x + 1, y, result)) {
                return true;
            };
        }

        // 右
        if(y + 1 < yMax && array[x][y+1] == 0) {
            if(boot(array, x, y + 1, result)) {
                return true;
            };
        }

        // 上
        if(x - 1 >= 0 && array[x-1][y] == 0) {
            if(boot(array, x - 1, y, result)) {
                return true;
            };
        }

        // 左
        if(y - 1 >= 0 && array[x][y-1] == 0) {
            if(boot(array, x, y - 1, result)) {
                return true;
            };
        }

        // 回溯
        result.remove(result.size() - 1);
        array[x][y] = 0;

        return false;
    }
}

//class A {
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        // 注意 hasNext 和 hasNextLine 的区别
//        while (in.hasNextInt()) { // 注意 while 处理多个 case
//            int n = in.nextInt();
//            int m = in.nextInt();
//            // 构造迷宫
//            int[][] map = new int[n][m];
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    map[i][j] = in.nextInt();
//                }
//            }
//
//            // 路径存储的数组
//            List<Pos> path = new ArrayList<>();
//            // DFS 搜索路径
//            dfs(map, 0, 0, path);
//            // 输出
//            for (Pos p : path) {
//                System.out.println("(" + p.x + "," + p.y + ")");
//            }
//        }
//    }
//
//    // 返回值 标记是否找到可通行的路劲
//    public static boolean dfs(int[][] map, int x, int y, List<Pos> path) {
//        // 添加路径并标记已走
//        path.add(new Pos(x, y));
//        map[x][y] = 1;
//        // 结束标志
//        if (x == map.length - 1 && y == map[0].length - 1) {
//            return true;
//        }
//        // 向下能走时
//        if (x + 1 < map.length && map[x + 1][y] == 0) {
//            if (dfs(map, x + 1, y, path)) {
//                return true;
//            }
//        }
//        // 向右能走时
//        if (y + 1 < map[0].length && map[x][y + 1] == 0) {
//            if (dfs(map, x, y + 1, path)) {
//                return true;
//            }
//        }
//        // 向上能走时
//        if (x - 1 > -1 && map[x - 1][y] == 0) {
//            if (dfs(map, x - 1, y, path)) {
//                return true;
//            }
//        }
//        // 向左能走时
//        if (y - 1 > -1 && map[x][y - 1] == 0) {
//            if (dfs(map, x, y - 1, path)) {
//                return true;
//            }
//        }
//        // 回溯
//        path.remove(path.size() - 1);
//        map[x][y] = 0;
//        return false;
//    }
//
//    // 简单的位置类
//    public static class Pos {
//        int x;
//        int y;
//
//        public Pos(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
//}
