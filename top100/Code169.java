package top100;

import java.util.Map;

/**
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 */
public class Code169 {
    /**
     * 使用辅助结构
     * 统计元素出现的次数
     * @param nums
     * @return
     */
    public int majorityElement1(int[] nums) {
        Map<Integer,Integer> map = new java.util.HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (map.get(nums[i]) > nums.length / 2) {
                return nums[i];
            }
        }
        return -1; // 理论上不会到达这里，因为题目保证存在多数元素
    }

    /**
     * 排序
     */
    public int majorityElement2(int[] nums) {
        java.util.Arrays.sort(nums);
        return nums[nums.length / 2]; // 中位数即为多数元素
    }

    /**
     * 进阶： 时间复杂度 O(n) 空间复杂度 O(1)
     *
     * 尝试设计一个 O(n) 一次遍历，同时只用到 O(1) 额外空间的算法。
     *
     * 想象一众武林高手比武，谁会笑到最后？
     *
     * 我用「擂台赛」打比方：
     *
     * 擂主登场：nums[0] 成为初始擂主，生命值为 1。
     * 挑战者出现：遍历后续元素，作为挑战者。
     * 比武：如果挑战者与擂主属于同一门派（值相同），那么擂主生命值加 1，否则擂主生命值减 1。
     * 擂主更迭：如果比武后，擂主生命值降为 0（同归于尽），那么下一个挑战者成为新的擂主，生命值为 1。
     * 最后在擂台上的那人，便是武林盟主（严格众数）。
     * 为什么这样做是对的？
     *
     * 设出现次数最多的元素的出现次数为 a，其余元素的出现次数之和为 b=n−a。题目保证 a>b。
     *
     * 证明：上述过程中，每次擂主的生命值降为 0 时，相当于开了一个新的擂台赛，在 nums[i] 和 nums[i+1] 之间切一刀。这会把 nums 分成若干段。依次考察这些段：
     *
     * 对于除了最后一段的每一段，设严格众数在其中出现了 x 次，其余元素的出现次数之和为 y，必然有 x≤y。这可以用反证法证明，如果 x>y，
     * 那么严格众数血多，不可能被其余元素同归于尽，严格众数的生命值在这段结束时必然大于 0，矛盾。由此可得 a−x>b−y，意思是，把 a 减去 x，b 减去 y，所得到的 a
     * ′
     *   和 b
     * ′
     *   仍然满足 a
     * ′
     *  >b
     * ′
     *  。依此类推，每一段结束时，在剩余元素（未遍历到的元素）中，设出现次数最多的元素的出现次数为 a
     * ′
     *  ，其余元素的出现次数之和为 b
     * ′
     *  ，那么 a
     * ′
     *  >b
     * ′
     *   始终成立。
     * 对于最后一段，由于 a
     * ′
     *  >b
     * ′
     *  ，严格众数血多，不可能被其余元素同归于尽，严格众数的生命值最终必然大于 0，所以最后在擂台上的是严格众数。
     *
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/majority-element/solutions/3744717/on-mo-er-tou-piao-fa-yan-jin-zheng-ming-ww1zv/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @return
     */
    public int majorityElement3(int[] nums) {
        int times = 0;
        int ans = 0;
        for (int i = 0; i < nums.length;i++) {
            if (times ==0) {
                ans = nums[i];
                times++;
            }else if (nums[i] == ans) {
                times++;
            }else if (nums[i] != ans) {
                times--;
            }
        }
        return ans;
    }
}
