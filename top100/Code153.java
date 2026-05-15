package top100;

/**
 * 寻找旋转排序数组中的最小值
 *
 * 元素互不相同
 *
 * 你必须设计一个时间复杂度为 O(log n) 的算法来解决此问题。
 *
 * 旋转数组其实是被分成了“大数段” 和 “小数段” 我们的目标是找到 “小数段的第一个数”
 *
 *
 */
public class Code153 {
    /**
     *
     *
     *
     *
     设 x=nums[mid] 是现在二分取到的数。

     我们需要判断 x 和数组最小值的位置关系，谁在左边，谁在右边？

     把 x 与最后一个数 nums[n−1] 比大小：（为什么跟最后一个数进行比较 它是小数段的最后一个数）

     如果 x>nums[n−1]，那么可以推出以下结论：
     1.nums 一定被分成左右两个递增段；
     2.第一段的所有元素均大于第二段的所有元素；
     3.x 在第一段。
     4.最小值在第二段。
     5.所以 x 一定在最小值的左边。

     如果 x≤nums[n−1]，那么 x 一定在第二段。（或者 nums 就是递增数组，此时只有一段。）
     x 要么是最小值，要么在最小值右边。

     所以，只需要比较 x 和 nums[n−1] 的大小关系，就间接地知道了 x 和数组最小值的位置关系，从而不断地缩小数组最小值所在位置的范围，二分找到数组最小值。
     *

     问：能否与 nums[0] 比大小？

     答：可以，但要多写一点代码。假设 nums 有两段。如果 nums[mid]>nums[0]，那么 mid 在第一段，在最小值左边；
     否则，mid 要么是最小值，要么在最小值右边。这种写法需要在 (0,n) 中二分，如果二分结果等于 n，说明 nums 其实只有一段，答案是 nums[0]。

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

    /**
     * 跟 nums[0] 比大小
     *
     * 设 x=nums[mid] 是现在二分取到的数。
     *
     * 把 x 与第一个数 nums[0] 比大小：（nums[0]是大数段的第一个数）
     *
     * 如果 x >= nums[0]，那么可以推出以下结论：
     * 1. nums 要么只有一段（未旋转），要么 x 处于第一段（大的那段）；
     * 2. 第一段的所有元素均大于第二段的所有元素；
     * 3. x 在第一段，最小值在第二段，即最小值在 x 的右边。
     * 4. 所以令 left = mid + 1，向右收缩。
     *    特别地，相等（x == nums[0]）只发生在 mid == 0 时，
     *    nums[0] 本身就在第一段，最小值同样在右边，所以也令 left = mid + 1。
     *
     * 如果 x < nums[0]，那么 x 一定在第二段（小的那段）。
     * x 要么是最小值，要么在最小值右边。
     * 所以令 right = mid - 1，向左收缩。
     *
     * 二分结束后 left 就是最小值的下标。
     * 但若 left == n，说明所有元素都 >= nums[0]，数组根本没有旋转，
     * 此时只有一段，最小值就是 nums[0]。
     *
     * @param nums
     * @return
     */
    public int findMin2(int[] nums) {
        int n = nums.length;
        int left = 0;
        int num = nums[0];
        int right = n - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            // 等于的含义是 只要mid指向的数还没跌进“小数段” 我就继续向右收缩
            if (nums[mid] >= num) {
                left = mid + 1;  // mid在第一段（大的那段），最小值在右边
            } else {
                right = mid - 1; // mid在第二段（小的那段），最小值在左边
            }
        }
        // left==n说明数组没有旋转，最小值就是nums[0]
        return left == n ? nums[0] : nums[left];
    }

    /**
     * 与0进行比较
     *
     * @param nums
     * @return
     */
    public int findMin3(int[] nums) {
        int n = nums.length;
        int left = 0;
        int num = nums[0];
        int right = n - 1;
        if(nums[right] >= nums[left]) {
            return nums[left];
        }
        // 数组一定能分成两组  每组都是单调递增的 第一组都比第二组大
        while(left <= right) {
            int mid =  left + ((right - left) >> 1);
            // 相等 即使已经剔除了没有旋转的场景 这里的相等也是不可或缺
            if(nums[mid] >= num) {
                left = mid +1;
            }else{
                right = mid - 1;
            }
        }
        return nums[left];
    }



    /**
     * 找最大值
     *
     * @param nums
     * @return
     */
    public int findMax(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;

        // 如果数组没有旋转（单调递增），最大值直接就是末尾
        if (nums[0] <= nums[n - 1]) return nums[n - 1];

        while (left <= right) {
            int mid = (left + right) >>> 1;
            // 如果 mid 还在第一段（大的那段）
            if (nums[mid] >= nums[0]) {
                // 我们记录当前这个可能是最大值的位置，并尝试向右看还有没有更大的
                left = mid + 1;
            } else {
                // mid 已经掉进第二段（小的那段）了，最大值肯定在左边
                right = mid - 1;
            }
        }
        // 这里的逻辑关键点：
        // 当循环结束时，left 跨过了最大值，停在了最小值（第二段起点）上
        // 所以最大值是 nums[left - 1]
        return nums[left - 1];
    }


    static void main() {
        Code153 code153 = new Code153();
        int[] nums = {3, 1, 2};
        int result = code153.findMin(nums);
        System.out.println(result); // 输出 1
    }

}
