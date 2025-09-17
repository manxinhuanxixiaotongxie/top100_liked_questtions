package top100;

public class Code062 {
    public int uniquePaths(int m, int n) {
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
