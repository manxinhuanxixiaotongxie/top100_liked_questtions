package top100;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class Code128 {
    /**
     * 题目要求时间复杂度为O（n）
     *
     * 辅助数组 使用set进行去重  找到原始数组中所有不重复的数字
     *
     * 在不重复的数字中查找最长连续序列
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        // 首先本题是不是可以排序的 排序的时间复杂度默认最好是 O(nlogn)
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 0;
        for (int num : set) {
            // 由于这个限制 保证了每个数字只会被访问一次
            if (set.contains(num - 1)) continue;
            int y = num + 1;
            while (set.contains(y)) {
                y += 1;
            }
            ans = Math.max(ans, y - num);
        }
        return ans;
    }

    /**
     * 局部优化
     * <p>
     * 假设原始数据中 有size种不同的数字
     * <p>
     * 如果我们发现 最长的序列的长度已经大于等于 size / 2  其他种类的数组的数量不可能凑出长度比他还大 不然的话链长就会超过 size
     *
     * @param nums
     * @return
     */
    public int longestConsecutive2(int[] nums) {
        // 首先本题是不是可以排序的 排序的时间复杂度默认最好是 O(nlogn)
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 0;
        int size = set.size();
        for (int num : set) {
            if (set.contains(num - 1)) continue;
            int y = num + 1;
            while (set.contains(y)) {
                y += 1;
            }
            ans = Math.max(ans, y - num);
            if ((ans << 1) >= size) {
                break;
            }
        }
        return ans;
    }


}
