package top100;

/**
 * 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 * <p>
 * 本题是最大子数组和的乘法版本 参考code053
 *
 */
public class Code152 {
    /**
     * 暴力解法
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        // 必须以i位置开头
        int n = nums.length;
        int ans = nums[0];

        for (int i = n - 1; i >= 0; i--) {
            // 必须要i开头
            // 总共有多少个子数组
            // i到i
            int curMul = nums[i];
            int curMax = curMul;
            for (int j = i + 1; j < n; j++) {
                // 子数组 i到j
                curMul *= nums[j];
                if (curMul > curMax) {
                    curMax = curMul;
                }
            }
            ans = Math.max(ans, curMax);
        }

        return ans;
    }

    /**
     * 使用辅助数组
     *
     * @param nums
     * @return
     */
    public int maxProduct2(int[] nums) {
        int n = nums.length;
        int[] max = new int[n];
        int[] min = new int[n];
        max[n - 1] = nums[n - 1];
        min[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            // 计算当前位置最大值
            max[i] = Math.max(nums[i], Math.max(min[i + 1] * nums[i], max[i + 1] * nums[i]));
            // 计算当前位置的最小值
            min[i] = Math.min(nums[i], Math.min(min[i + 1] * nums[i], max[i + 1] * nums[i]));
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (max[i] > ans) {
                ans = max[i];
            }
        }
        return ans;
    }

    /**
     * 减少一次遍历
     *
     * @param nums
     * @return
     */
    public int maxProduct3(int[] nums) {
        int n = nums.length;
        int[] max = new int[n];
        int[] min = new int[n];
        max[n - 1] = nums[n - 1];
        min[n - 1] = nums[n - 1];
        int ans = nums[n-1];
        for (int i = n - 2; i >= 0; i--) {
            // 计算当前位置最大值
            max[i] = Math.max(nums[i], Math.max(min[i + 1] * nums[i], max[i + 1] * nums[i]));
            // 计算当前位置的最小值
            min[i] = Math.min(nums[i], Math.min(min[i + 1] * nums[i], max[i + 1] * nums[i]));
            ans = Math.max(ans, max[i]);
        }

        return ans;
    }


    /**
     * 压缩空间做法：
     * 观察相对位置 当前位置最大值收到下一个位置最大值 最小值的影响
     * <p>
     * <p>
     * 必须以i位置开头
     * <p>
     * 维护两个信息 右端点下标为i的子数组的最大乘积 和 最小乘积
     *
     * @param nums
     * @return
     */
    public int maxProduct4(int[] nums) {
        int n = nums.length;
        int max = nums[n - 1], min = nums[n - 1];
        int ans = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            // 必须以i位置开头
            int mx = max;
            max = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
            min = Math.min(nums[i], Math.min(mx * nums[i], min * nums[i]));
            ans = Math.max(ans, max);
        }

        return ans;
    }

}
