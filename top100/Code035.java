package top100;

public class Code035 {
    // 必须使用O(log n)的时间复杂度
    public int searchInsert(int[] nums, int target) {
        // 二分查找 闭区间
        /**
         * 找到第一个大于等于target的元素
         */
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
