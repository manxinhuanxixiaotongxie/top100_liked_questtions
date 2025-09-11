package top100;

/**
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9
 * 而3 和11 不是完全平方数。
 */
public class Code279 {
    /**
     * 更优尝试
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        // 最大尝试数量不会超过n的一半
        return process(n);
    }

    // 返回组合结果为n的最小数量
    public int process(int n) {
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            ans = Math.min(ans, process(n - i * i) + 1);
        }
        return ans;
    }

    /**
     * 将上面的方法改成DP
     * @param n
     * @return
     */
    public int numSquares2(int n) {
        // 最大尝试数量不会超过n的一半
        int[] dp = new int[n + 1];
        for (int i = 1; i <=n;i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 两个参数的尝试 不是最优解
     *
     * @param n
     * @return
     */
    public int numSquares3(int n) {
        // 最大尝试数量不会超过n的一半
        // 第二个参数不能使用n>>1 因为会漏掉一些情况 比如1 移动之后变成0
        return process3(n, (int) Math.sqrt(n));
    }

    // 返回组合结果为n的最小数量
    public int process3(int n, int tryNum) {
        if (n < 0) {
            // 方案无效
            return Integer.MAX_VALUE;
        }
        if (n == 0) {
            // 方案有效
            return 0;
        }
        // 普遍位置尝试
        // 从tryNum开始尝试
        int ans = Integer.MAX_VALUE;
        for (int i = tryNum; i > 0; i--) {
            // 这里第二个参数不能使用 n - (i * i) >> 1 会漏掉 n-(i*i)为1的情况
            int next = process3(n - (i * i), i);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(ans, next + 1);
//                break;
                // 这里不能加break，因为可能不是最优解
                // 以12举例子 3*3+1*1+1*1  2*2*2  第一种方案四个数字 第二种方案三个数字
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code279 code279 = new Code279();
        int ans = code279.numSquares(12);
        System.out.println(ans);
        int ans2 = code279.numSquares2(12);
        System.out.println(ans2);
    }
}
