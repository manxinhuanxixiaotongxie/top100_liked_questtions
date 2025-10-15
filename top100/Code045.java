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
     * 什么情况下才建桥：
     * 1.发现走不下去了 必须要构建一个桥梁才能允许继续前进
     * 2.桥梁建造在什么位置 其实是一个虚拟的概念 建造的桥梁是在能够到达最右端点的位置进行建造的
     *
     * 循环为什么写到N-1就结束  因为 到达n-2位置之后只有两种情况 要么已经可以到达n-1位置 要么需要再建造最后一座桥
     *
     * 思考一个问题：如果题目没有保证一定能够到达n-1位置 代码应该怎么改写
     *
     *
     *
     * @param nums
     * @return
     */
    public int jump3(int[] nums) {
        int ans = 0;
        // 需要造桥的时机
        // 为什么需要造桥 就是因为走不下去了
        int mostRight = 0;
        // 造桥最远的点
        int nextMostRight = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            nextMostRight = Math.max(nextMostRight, nums[i] + i);
            if (i == mostRight) {
                // 必须建桥
//                if (i == nextMostRight) {
//                    // 说明走不下去了
//                    return -1;
//                }
                mostRight = nextMostRight;
                ans++;
            }
        }
        return ans;
    }

}
