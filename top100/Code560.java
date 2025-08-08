package top100;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * <p>
 * 子数组是数组中元素的连续非空序列。
 */
public class Code560 {
    /**
     * 子数组的问题
     * 优先考虑以i开头 或者以i结尾的子数组
     * 本题采用以i结尾的子数组
     * 1.先求前缀和
     * 2. 如果以i结尾的子数组的和为k 此时sum[i]为一个数
     * 等效于求以i结尾之前的有多少个子数组的前缀和在sum[i] -k
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        // 必须以i位置结尾的字数组
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = nums[i] + sum[i - 1];
        }
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            ans += map.getOrDefault(sum[i] - k, 0);
            map.put(sum[i], map.getOrDefault(sum[i], 0) + 1);
        }
        return ans;
    }
}
