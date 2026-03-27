package top100;

import java.util.Arrays;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 */
public class Code322 {
    public int coinChange(int[] coins, int amount) {
        int ans = process(coins, amount, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int process(int[] coins, int amount, int index) {
        if (index == coins.length) {
            return amount == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (amount < 0) {
            return Integer.MAX_VALUE;
        }
        // 当前硬币
        int ans = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * coins[index] <= amount; zhang++) {
            int next = process(coins, amount - zhang * coins[index], index + 1);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(next + zhang, ans);
            }
        }
        return ans;
    }

    /**
     * 改动态规划
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        // 将数组从大到小排序
        Arrays.sort(coins);
        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        for (int i = 1; i < amount + 1; i++) {
            dp[N][i] = Integer.MAX_VALUE;
        }
        dp[N][0] = 0;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= amount; rest++) {
                dp[index][rest] = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * coins[index] <= rest; zhang++) {
                    int next = rest - zhang * coins[index] < 0 ? Integer.MAX_VALUE :
                            dp[index + 1][rest - zhang * coins[index]];
                    if (next != Integer.MAX_VALUE) {
                        dp[index][rest] = Math.min(next + zhang, dp[index][rest]);
                    }
                }
            }

        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }

    /**
     * 四边形不等式优化
     * 将动态规划修改O（N ^ 2）
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange3(int[] coins, int amount) {
        // 将数组从大到小排序
        Arrays.sort(coins);
        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        for (int i = 1; i < amount + 1; i++) {
            dp[N][i] = Integer.MAX_VALUE;
        }
        dp[N][0] = 0;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= amount; rest++) {
                dp[index][rest] = Integer.MAX_VALUE;
                if (rest - coins[index] >= 0 && dp[index][rest - coins[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = dp[index][rest - coins[index]] + 1;
                }
                dp[index][rest] = Math.min(dp[index][rest], dp[index + 1][rest]);

//                for (int zhang = 0; zhang * coins[index] <= rest; zhang++) {
//                    int next = rest - zhang * coins[index] < 0 ? Integer.MAX_VALUE :
//                            dp[index + 1][rest - zhang * coins[index]];
//                    if (next != Integer.MAX_VALUE) {
//                        dp[index][rest] = Math.min(next + zhang, dp[index][rest]);
//                    }
//                }
            }

        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }

    public static void main(String[] args) {
        Code322 code322 = new Code322();
        int[] arr = {1, 2, 5};
        System.out.println(code322.coinChange(arr, 11));
        System.out.println(code322.coinChange2(arr, 11));
        System.out.println(code322.coinChange3(arr, 11));
    }
}
