package top100;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 */
public class Code300 {
    /**
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        return process(nums, 0, Integer.MIN_VALUE);
    }

    /**
     *
     * 这个尝试的是当前位置要或者不要
     *
     * @param nums
     * @param index
     * @param pre
     * @return
     */
    public int process(int[] nums, int index, int pre) {
        if (index == nums.length) {
            return 0;
        }
        // 当前位置不要
        int p1 = process(nums, index + 1, pre);
        // 当前位置要
        if (nums[index] > pre) {
            int p2 = 1 + process(nums, index + 1, nums[index]);
            return Math.max(p1, p2);
        } else {
            return p1;
        }
    }

    /**
     * 将方法1改成记忆化搜索
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        Map<String, Integer> map = new HashMap<>();
        return process2(nums, 0, Integer.MIN_VALUE, map);
    }

    public int process2(int[] nums, int index, int pre, Map<String, Integer> map) {
        if (index == nums.length) {
            return 0;
        }
        String key = index + "_" + pre;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        // 当前位置不要
        int p1 = process2(nums, index + 1, pre, map);
        // 当前位置要
        if (nums[index] > pre) {
            int p2 = 1 + process2(nums, index + 1, nums[index], map);
            int ans = Math.max(p1, p2);
            map.put(key, ans);
            return ans;
        } else {
            map.put(key, p1);
            return p1;
        }
    }


    /**
     * 第二种尝试方法
     * 枚举所有的位置
     */

    public int lengthOfLIS3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(process3(nums, i), ans);
        }
        return ans;
    }

    public int process3(int[] nums, int index) {
        int ans = 1;
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] > nums[index]) {
                ans = Math.max(ans, process3(nums, i) + 1);
            }
        }
        return ans;
    }

    /**
     * 改动态规划
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS4(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        dp[n - 1] = 1;
        for (int index = n - 2; index >= 0; index--) {
            dp[index] = 1;
            for (int i = index + 1; i < n; i++) {
                if (nums[i] > nums[index]) {
                    dp[index] = Math.max(dp[index], dp[i] + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    /**
     * 总结：
     * 重要：
     * 什么的题目适合【选或者不选】 什么样的题目适合【枚举选哪个】
     *
     * 两类问题：
     * 1.相邻无关子序列问题 比如背包 适合选或者不选 每个元素互相独立 只需要一次考虑每个物品选或者不选
     *
     *
     * 2.相邻相关子序列问题 本如本题 适合枚举选哪个 我们需要知道子序列相邻两个数的关系
     * 对于本题来说 枚举nums[i]必须 然后枚举前一个必选的数 方便比大小 如果硬要用选或者不选 需要额外
     * 记录上一个选的数的下标 算法总体的空间复杂度为O(N^2) 而枚举只需要用O(N)的空间复杂度
     *
     *
     */

}
