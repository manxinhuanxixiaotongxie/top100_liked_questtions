package top100;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。
 * 滑动窗口每次只向右移动一位。
 * <p>
 * 返回 滑动窗口中的最大值 。
 */
public class Code239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 滑动窗口 最大值的滑动窗口 保证窗口的最大值在头部
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k - 1; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int[] ans = new int[nums.length - k + 1];
        int index = 0;
        for (int i = k - 1; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            ans[index++] = nums[deque.peekFirst()];
            if (deque.peekFirst() == i - k + 1) {
                deque.pollFirst();
            }
        }
        return ans;
    }
}
