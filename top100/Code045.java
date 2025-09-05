package top100;

/**
 * 跳跃游戏2
 * 给定一个长度为N的0索引整数数组nums，初始位置为数组的第一个元素。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个元素
 *
 *
 * 区间贪心进阶题目   code1326
 *
 *
 *
 */
public class Code045 {
    /**
     * 暴力解
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        return process(nums, 0);
    }

    public int process(int[] nums, int index) {
        if (index >= nums.length - 1) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int step = 1; step <= nums[index]; step++) {
            int next = process(nums, index + step);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(ans, next + 1);
            }
        }
        return ans;
    }

    /**
     * 将上面的方法改成动态规划
     *
     * @param nums
     * @return
     */
    public int jump2(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        dp[N-1] = 0;
        for (int i = N - 2; i >= 0; i--) {
            int ans = Integer.MAX_VALUE;
            for (int step = 1; step <= nums[i]; step++) {
                int next = i + step >= N - 1 ? 0 : dp[i + step];
                if (next != Integer.MAX_VALUE) {
                    ans = Math.min(ans, next + 1);
                }
            }
            dp[i] = ans;
        }
        return  dp[0];
    }

    /**
     * 思路：参考灵神题解  贪心
     *
     * 想象从0位置出发走到nums.length-1位置的过程
     *
     *
     * @param nums
     * @return
     */
    public int jump3(int[] nums) {
        int ans = 0;
        int mostRight = 0;
        int nextMostRight = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            nextMostRight = Math.max(nextMostRight, nums[i] + i);
            if (i == mostRight) {
                // 必须建桥
                mostRight = nextMostRight;
                ans++;
            }
        }
        return ans;
    }

}
