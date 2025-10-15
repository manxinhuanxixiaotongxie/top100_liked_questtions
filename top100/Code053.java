package top100;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 子数组是数组中的一个连续部分。
 */
public class Code053 {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int ans = nums[nums.length - 1];
        int pre = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            if (pre < 0) {
                pre = nums[i];
                ans = Math.max(ans, pre);
            } else {
                pre += nums[i];
                ans = Math.max(ans, pre);
            }
        }
        return ans;
    }
}
