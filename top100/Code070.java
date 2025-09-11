package top100;

public class Code070 {

    public int climbStairs(int n) {
        return process(n, 0);
    }

    public int process(int n, int index) {
        if (index == n) {
            return 1;
        }
        if (index > n) {
            return 0;
        }
        return process(n, index + 1) + process(n, index + 2);
    }

    /**
     * 改动态规划
     */

    public int climbStairs2(int n) {
        if (n < 2) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[n] = 1;
        dp[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] + dp[i + 2];
        }
        return dp[0];
    }

    /**
     * 空间优化
     */
    public int climbStairs3(int n) {
        if (n < 2) {
            return 1;
        }
        int pre = 1;
        int cur = 1;
        for (int i = n - 2; i >= 0; i--) {
            int temp = cur;
            cur = cur + pre;
            pre = temp;
        }
        return cur;
    }
}
