package com.deepblue.inaction_100_source_algorithm_back.huawei.test_003.sectionmerge;

import com.alibaba.fastjson.JSON;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        int[][] intervals = {{1,2}, {2,14}, {8,10}, {15,18}};
        int[][] merge = merge(intervals);
        System.out.println(JSON.toJSONString(merge));

    }

    public static int[][] merge(int[][] intervals) {

        int count = 0;
        for(int i = 0; i < intervals.length - 1; i++) {
            if(mergeFlag(intervals[i], intervals[i+1])) {
                int p = intervals[i][0];
                int q = intervals[i][1];
                int m = intervals[i+1][0];
                int n = intervals[i+1][1];

                int a = Math.min(p, m);
                int b = Math.max(q, n);

                intervals[i][0] = Integer.MIN_VALUE;
                intervals[i][1] = Integer.MIN_VALUE;
                intervals[i+1][0] = a;
                intervals[i+1][1] = b;

                count ++;
            }
        }

        int[][] result = new int[intervals.length - count][];
        int index = 0;
        for(int i = 0; i < intervals.length; i++) {
            if(intervals[i][0] != Integer.MIN_VALUE && intervals[i][1] != Integer.MIN_VALUE) {
                result[index] = intervals[i];
                index++;
            }
        }

        return result;
    }

    public static boolean mergeFlag(int[] base, int[] param) {

        int i = base[0];
        int j = base[1];
        int m = param[0];
        int n = param[1];

        if(i <= j && m <= n && (j < m || n < i)) {
            return false;
        }

        return true;
    }
}
