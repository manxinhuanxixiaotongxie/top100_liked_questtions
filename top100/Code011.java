package top100;

/**
 * 盛水最多的容器
 */
public class Code011 {
    /**
     * 第一种解法 使用双指针
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        // 至少也得有三个长度才能盛水
        if (height == null || height.length < 2) {
            return 0;
        }
        int ans = 0;
        // 维护一个左指针
        int left = 0;
        // 维护一个有指针
        int right = height.length - 1;
        while (left < right) {
            // 以left作为左边界 right作为右边界
            // 能够存水的空间就是right-left
            // 高度的瓶颈就是两边的最小高度
            int minHeight = Math.min(height[left], height[right]);
            ans = Math.max(ans, minHeight * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }
}
