package top100;

public class Code287 {
    // 寻找重复数
    // 不修改数组 且只用常量级的额外空间
    public int findDuplicate(int[] nums) {
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
