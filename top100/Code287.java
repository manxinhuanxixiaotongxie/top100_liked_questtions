package top100;

/**
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 * <p>
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 * <p>
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 *
 */
public class Code287 {

    public int findDuplicate1(int[] nums) {
        // 找数字
        for (int i = 0; i < nums.length;) {
            // 当前位置的数刚好是相等的 i直接++即可
            // 当前位置的数比i+1还要大 并且要发货的位置已经占用 直接返回
            // 当前位置的数比i+1要小 前面已经占用了 直接返回
            if (nums[nums[i]] == nums[i]) {
                return nums[i];
            } else if (nums[i] == i) {
                i++;
            } else if (nums[i] > i && nums[nums[i]] != nums[i]) {
                swap(nums, i, nums[i]);
            } else if (nums[i] < i) {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 交换
     *
     * @param nums
     * @param i
     * @param j
     */
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 寻找重复数
    // 不修改数组 且只用常量级的额外空间
    public int findDuplicate2(int[] nums) {
        if (nums.length == 2) {
            return nums[0];
        }
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
