package top100;

/**
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * <p>
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 */
public class Code041 {
    public int firstMissingPositive(int[] nums) {
        // 缺失的第一个正数
        // 双指针
        // 从0-left  0位置的放1 1位置放2 2位置放3
        // right到之前是需要放弃位置
        int N = nums.length;
        int left = 0;
        // 垃圾区
        int right = N - 1;
        while (left <= right) {
            if (nums[left] == left + 1) {
                left++;
            }
            // 发放到垃圾区的条件
            // 本意是为了0位置放1 1位置放2 2位置放3.。。。
            // 第一种情况 当前位left来到了4位置 但是当前位置是3
            // 第二种情况当前值是5 但是后面4位置已经有了一个5
            // 第三种情况假设垃圾区的index是8 那么说明可能缺失的正数在1-9 突然来了10
            else if (nums[left] < left + 1 || nums[left] > right + 1 || nums[nums[left] - 1] == nums[left]) {
                swap(nums, left, right--);
            } else {
                // 交换
                // 当前位置是5 就将他发配到index = 4的位置 当前left不做处理
                swap(nums, nums[left] - 1, left);
            }
        }
        return left + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
