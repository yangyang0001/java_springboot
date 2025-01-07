package com.deepblue.inaction_100_source_algorithm_back.huawei.test_009.money;

/**
 * 动态规划:
 * coins  表示 硬币的种类
 * amount 表示 要用硬币组成的 数值
 *
 * 求出: 组成 amount 的面值的硬币 有多少种 组成方式!
 */
public class Main {


    public static void main(String[] args) {

        int[] coins = {1, 2, 5};
        int amount = 10;

        int i = coinChange(coins, amount);
        System.out.println(i);

    }


    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for(int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }

        return dp[amount];
    }



}
