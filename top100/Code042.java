package top100;

import java.util.Stack;

/**
 * 接雨水问题
 */
public class Code042 {
    /**
     * 思路 单调栈
     *
     * @param height
     * @return
     */
    public int trap1(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] >= height[stack.peek()]) {
                int pop = stack.pop();
                // 小到大
                ans += stack.isEmpty() ? 0 : (Math.min(height[i], height[stack.peek()]) - height[pop]) * (i - stack.peek() - 1);
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * 双指针
     * <p>
     * 这样想： 每个位置能接的雨水的最大值
     * 其实是左边的限制 与右边的限制  两者取最小值
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];
        int ans = 0;
        while (left < right) {
            if (height[left] > height[right]) {
                right--;
                ans += Math.max(0, rightMax - height[right]);
                rightMax = Math.max(rightMax, height[right]);
            } else if (height[left] <= height[right]) {
                left++;
                ans += Math.max(0, leftMax - height[left]);
                leftMax = Math.max(leftMax, height[left]);
            }
        }
        return ans;
    }


}
