package top100;

/**
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 * <p>
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，
 * 那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * <p>
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 */
public class Code031 {
    /**
     * 灵神的题解
     * <p>
     * <p>
     * 排列： 1 3 5 4 2
     * <p>
     * 第一步：.从右向左，找第一个数字 x，满足 x 右边有大于 x 的数，这样可以把 x 变大。我们找到的数是 3。注意到，3 右边的数是递减的（证明见答疑），所以 3 右侧相邻数字就是 3 右边最大的数。
     * 如果 3 右侧相邻数字小于 3，那么 3 右边必然没有大于 3 的数。因此，这一步可以简化为，从右向左，找第一个小于右侧相邻数字的数 x。
     * <p>
     * 右边是递减的 这个特性很关键
     * <p>
     * 证明：我们的流程是找到第一个数x 那么在x的右侧一定存在大于X的数  如果X后面的位置不是递减的话 那么在x之前一定能能找到一个index位置大于x的index的位置的数 使得nums[i] > nums[i+1]
     * 这与我们的流程是相悖的  反证法
     * <p>
     * <p>
     * 第二步：  找 3 右边最小的大于 3 的数，即 4。由于 3 右边的数是递减的，所以从右向左找到的第一个大于 3 的数，就是 3 右边最小的大于 3 的数。
     * 然后把 4 放到 3 的位置上，把 3 放到右边的三个位置中。这一步可以简化为交换 3 和 4。交换后得到 [1,4,5,3,2]。注意交换后 5,4,2 变成 5,3,2，仍然是递减的（证明见答疑）。
     * <p>
     * 证明：因为x右侧的数是递减的， 我们从右向左的查找过程中 查找的是第一个大于x的数 即使我们交换x与找到数y进行交换 交换之后依然是递减的
     * <p>
     * <p>
     * 第三步：把 4 右边的数从小到大排序。由于第二步交换后，4 右边的数 5,3,2 是递减的，所以只需要把 5,3,2 反转，就得到了答案 [1,4,2,3,5]。
     * <p>
     * <p>
     * 特别的：如果是 1 2 3 这种数据 我们是怎么算的？
     * 第一步：找到1位置的2   2 位置3
     * 第二步：从右到左找到第一个大于大于2 的数3 进行交换 数组变成 1 3 2
     * 第三步： 1位置后面只有一个数2 进行翻转 数组还是 1 3 2
     * <p>
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/next-permutation/solutions/3621022/jiao-ni-cong-ling-kai-shi-si-kao-zhe-ti-9qfrq/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     * 把上述过程一般化 同时对比下一个整数的算法：
     *
     * 下一个整数：
     * 第一步：从右到左 找到第一个小于9的数x
     * 第二步：把x+1 得到y=x+1
     * 第三步:把y右边的数都变成最小的个位数
     *
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        if (nums.length == 2) {
            swap(nums, 0, 1);
            return;
        }
        // 必须原地修改 只允许使用额外常数空间
        int index = nums.length - 2;
        // 找到第一个nums[index+1] > nums[index] 的位置
        while (index >= 0 && nums[index] >= nums[index + 1]) {
            index--;
        }
        if (index == -1) {
            // 交换 0 -numss.leng-1
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                swap(nums, left++, right--);
            }
        } else {
            // 在右侧找到第一个数是大于当前数
            int rightIndex = nums.length - 1;
            while (nums[rightIndex] <= nums[index]) {
                rightIndex--;
            }
            // rightIndex来到了第一个大于
            // 交换
            swap(nums, index, rightIndex);
            int temp = nums.length - 1;
            while (index < temp) {
                // 交换index+1 - nums.length-1
                swap(nums, ++index, temp--);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Code031 code031 = new Code031();
        int[] nums = {1,  2};
        code031.nextPermutation(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
