package top100;

/**
 * 给你一个只包含正整数的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 */
public class Code416 {
    /**
     * 什么样的题目适合采用背包问题 当前数选或者不选
     * 什么样的题目适合采用的枚举方式
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if ((sum & 1) != 0) return false;
        // 偶数 可以分割
        int target = sum >> 1;
        int n = nums.length;
        return process(nums, 0, target);
    }

    private boolean process(int[] nums, int index, int rest) {
        if (rest == 0) return true;
        if (index == nums.length) return false;
        // 当前数要么要 要么不要
        // 第一种情况 当前数不要
        boolean ans = process(nums, index + 1, rest);
        // 当前数要 但是是有前提的
        if (rest >= nums[index]) {
            // 当前数可以要
            ans |= process(nums, index + 1, rest - nums[index]);
        }
        return ans;
    }

    /**
     * 改动态规划
     */
    public boolean canPartition2(int[] nums) {
        // 改动态规划
        int sum = 0;
        for (int num : nums) sum += num;
        if ((sum & 1) != 0) return false;
        // 偶数 可以分割
        int target = sum >> 1;
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][target + 1];
        // 先填第一列
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 1; rest <= target; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest >= nums[index]) {
                    dp[index][rest] |= dp[index + 1][rest - nums[index]];
                }
            }
        }
        return dp[0][target];
    }

    /**
     * 空间压缩
     * 每一行只依赖下一行的数
     */
    public boolean canPartition3(int[] nums) {
        // 改动态规划
        int sum = 0;
        for (int num : nums) sum += num;
        if ((sum & 1) != 0) return false;
        // 偶数 可以分割
        int target = sum >> 1;
        int n = nums.length;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        // 当前行只依赖下一行的位置的数
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 1; rest <= target; rest++) {
                if (rest >= nums[index]) {
                    dp[rest] |= dp[rest - nums[index]];
                    if (dp[rest]) return true;
                }
            }
        }

        return dp[target];
    }
}
