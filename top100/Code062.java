package top100;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * <p>
 * 问总共有多少条不同的路径？
 *
 */
public class Code062 {

    /**
     *
     * 补充暴力递归解法
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        return process(m , n , 0, 0);
    }

    public int process(int m, int n, int curM, int curN) {
        if (curM == m-1 && curN == n-1) return 1;
        // 最后一列 只能向下走
        if (curN == n-1) {
            return process(m,n,curM+1,curN);
        }else if (curM == m-1) {
            return  process(m,n,curM,curN+1);
        }else {
            int right = process(m,n,curM,curN+1);
            int down = process(m,n,curM+1,curN);
            return right + down;
        }
    }

    public int uniquePaths2(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1;
        for (int r1 = m - 1; r1 >= 0; r1--) {
            for (int c1 = n - 1; c1 >= 0; c1--) {
                if (r1 == m - 1) {
                    // 向右走
                    dp[r1][c1] += c1 < n - 1 ? dp[r1][c1 + 1] : 0;
                } else if (c1 == n - 1) {
                    // 下个走
                    dp[r1][c1] += r1 < m - 1 ? dp[r1 + 1][c1] : 0;
                } else {
                    // 向右走
                    dp[r1][c1] += dp[r1][c1 + 1];
                    // 下个走
                    dp[r1][c1] += dp[r1 + 1][c1];
                }
            }
        }

        return dp[0][0];
    }
}
