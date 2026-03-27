package top100;

/**
 * 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组 不被视为 额外空间。）
 *
 */
public class Code238 {
    public int[] productExceptSelf(int[] nums) {
        int mulLeft = 1;
        int[] help = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            help[i] = mulLeft;
            mulLeft *= nums[i];
        }
        mulLeft = 1;
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = mulLeft * help[i];
            mulLeft *= nums[i];
        }
        return ans;
    }

    /**
     * 进阶做法
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        ans[N-1] = 1;
        for (int i = N-2;i>=0;i--) {
            ans[i] = ans[i+1] * nums[i+1];
        }
        int pre = 1;
        for (int i = 0; i < N;i++) {
            ans[i] = ans[i] * pre;
            pre *= nums[i];
        }

        return ans;
    }
}
