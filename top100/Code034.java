package top100;

public class Code034 {
    /**
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * <p>
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * <p>
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        // 数组非递减
        int left = 0;
        int right = nums.length - 1;
        // 找到最左侧的位置
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int[] ans = new int[] {-1, -1};
        ans[0] = left == nums.length || nums[left] != target ? -1 : left;
        if (ans[0] == -1) {
            return ans;
        }
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < (target + 1)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        ans[1] = left == 0 && nums[left] != target ||  nums[left -1] != target ? -1 : left - 1;
        return ans;
    }

    public static void main(String[] args) {
        Code034 code034 = new Code034();
        int[] nums = {};
        int target = 0;
        int[] ans = code034.searchRange(nums, target);
        System.out.println(ans[0] + " " + ans[1]);
    }
}
