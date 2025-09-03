package top100;

/**
 * 寻找旋转排序数组中的最小值
 * 你必须设计一个时间复杂度为 O(log n) 的算法来解决此问题。
 */
public class Code153 {
    /**
     * 设X=nums[mid]是现在二分取到的数
     * 我们需要判断x和数组最小值的位置关系 谁在左边 谁在右边
     *
     * 把x和最后一个数nums[n-1]比较大小：
     * 第一种情况：x>nums[n-1] 说明x在左边，最小值在右边
     * 第二种情况：x<=nums[n-1] 说明x在右边，最小值在x左边或者就是x
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1; // 开区间 (-1, n-1)
        while (left <= right) { // 开区间不为空
            int mid = (left + right) >>> 1;
            if (nums[mid] <= nums[n - 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }

}
