package top100;

/**
 * 跳跃游戏问题
 * 判断是否能够到达最后一个下标 如果能返回true 否则返回false
 */
public class Code055 {
    /**
     * 思路：暴力解法
     * 暴力尝试每个位置能跳跃的步数
     * 如果能到达最后一个位置返回true
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        return process(nums, nums.length - 1, 0);
    }

    public boolean process(int[] nums, int n, int index) {
        if (index >= n) {
            return true;
        }
        boolean ans = false;
        for (int i = 1; i <= nums[index]; i++) {
            ans |= process(nums, n, index + i);
        }
        return ans;
    }

    /**
     * 思路参考灵神
     * 遍历数组的同时 维护最右侧可以到达的位置mx
     *
     * 算法如下：
     * 1.从左到右遍历数组 同事维护最右侧可以到达的位置mx 初始值为0
     * 2.如果i > mx 说明当前位置不可达 直接返回false
     * 3.更新mx = max(mx, i + nums[i]) 表示当前位置能到达的最远位置
     * 4.如果的没有返回false 说明能到达最后一个位置 返回true
     *
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        int mx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > mx) return false;
            mx = Math.max(mx,i + nums[i]);
        }
        return true;
    }
}
