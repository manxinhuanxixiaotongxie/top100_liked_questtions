package top100;

/**
 * 你是一个专业的小偷 ，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 */
public class Code198 {

    public int rob(int[] nums) {
        return process(nums, nums.length, 0);
    }

    public int process(int[] nums, int n, int index) {
        if (index >= n) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        // 当前位置不要 去下个房间去偷
        int p1 = process(nums, n, index + 1);
        ans = Math.max(ans, p1);
        // 当前位置要 去下下个房间去偷
        int p2 = nums[index] + process(nums, n, index + 2);
        ans = Math.max(ans, p2);
        return ans;
    }

    /**
     * 改动态规划
     */

    public int rob2(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N + 1];
        for (int i = N - 1; i >= 0; i--) {
            int p1 = dp[i + 1];
            int p2 = nums[i] + ((i + 2 > N) ? 0 : dp[i + 2]);
            dp[i] = Math.max(p1, p2);
        }
        return dp[0];
    }

    /**
     *
     * @param nums
     * @return
     */
    public int rob3(int[] nums) {
        int N = nums.length;
        int cur = nums[N - 1];
        int pre = 0;
        for (int i = N - 2; i >= 0; i--) {
            int temp = cur;
            cur = Math.max(pre + nums[i], cur);
            pre = temp;
        }
        return cur;
    }
}
