package top100;

import java.util.Arrays;
import java.util.Comparator;

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
                ans =  Math.min(next + zhang, ans);
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
        int ans = process(coins, amount, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        Code322 code322 = new Code322();
        int[] arr = {1, 2, 5};
        System.out.println(code322.coinChange(arr, 11));
    }
}
