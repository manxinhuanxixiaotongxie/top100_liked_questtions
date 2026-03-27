package top100;

/**
 * 寻找旋转排序数组中的最小值
 *
 * 元素互不相同
 *
 * 你必须设计一个时间复杂度为 O(log n) 的算法来解决此问题。
 */
public class Code153 {
    /**
     *
     * 假设x = nums[mid] 现在二分取到的数
     *
     * 我们需要判断 x和数组最小值的位置关系 谁在左边  谁在右边
     *
     * 把 x 与最后一个数 nums[n−1] 比大小：
     *
     * 如果 x>nums[n−1]，那么可以推出以下结论：
     * nums 一定被分成左右两个递增段；
     * 第一段的所有元素均大于第二段的所有元素；
     * x 在第一段。
     * 最小值在第二段。
     * 所以 x 一定在最小值的左边。
     * 如果 x≤nums[n−1]，那么 x 一定在第二段。（或者 nums 就是递增数组，此时只有一段。）
     * x 要么是最小值，要么在最小值右边。
     * 所以，只需要比较 x 和 nums[n−1] 的大小关系，就间接地知道了 x 和数组最小值的位置关系，从而不断地缩小数组最小值所在位置的范围，二分找到数组最小值。


     问：能否与 nums[0] 比大小？

     答：可以，但要多写一点代码。假设 nums 有两段。如果 nums[mid]>nums[0]，那么 mid 在第一段，在最小值左边；否则，mid 要么是最小值，要么在最小值右边。这种写法需要在 (0,n) 中二分，如果二分结果等于 n，说明 nums 其实只有一段，答案是 nums[0]。

     *
     *
     *
     *
     *
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

    static void main() {
        Code153 code153 = new Code153();
        int[] nums = {3, 1, 2};
        int result = code153.findMin(nums);
        System.out.println(result); // 输出 1
    }

}
