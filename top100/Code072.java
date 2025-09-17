package top100;

/**
 * 编辑距离
 * <p>
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 */
public class Code072 {

    public int minDistance(String text1, String text2) {
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        if (str1.length == 0) return str2.length;
        if (str2.length == 0) return str1.length;
        // 返回将字符串从text1转换成text2需要的最小操作数
        return process(str1, str2, str1.length - 1, str2.length - 1);
    }

    /**
     * 定义一个递归函数
     * str1从0-index1转换成str2从0-index2需要的最小操作数
     */
    public int process(char[] str1, char[] str2, int index1, int index2) {
        if (index1 == 0 && index2 == 0) {
            return str1[0] == str2[0] ? 0 : 1;
        } else if (index1 == 0) {
            // index2不为0
            return str1[0] == str2[index2] ? index2 : process(str1, str2, 0, index2 - 1) + 1;
        } else if (index2 == 0) {
            return str1[index1] == str2[0] ? index1 : process(str1, str2, index1 - 1, 0) + 1;
        } else {
            // 普遍位置
            int ans = Integer.MAX_VALUE;
            // 第一种情况
            if (str1[index1] == str2[index2]) {
                ans = Math.min(ans, process(str1, str2, index1 - 1, index2 - 1));
            } else {
                // 不相等
                // 第一种情况
                ans = Math.min(ans, process(str1, str2, index1 - 1, index2 - 1) + 1);
                // 删除
                ans = Math.min(ans, process(str1, str2, index1 - 1, index2) + 1);
                // 插入
                ans = Math.min(ans, process(str1, str2, index1, index2 - 1) + 1);
            }
            return ans;
        }
    }

    /**
     * 改成动态规划
     *
     */
    public int minDistance2(String text1, String text2) {
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        if (str1.length == 0) return str2.length;
        if (str2.length == 0) return str1.length;
        // 返回将字符串从text1转换成text2需要的最小操作数

        int N1 = str1.length;
        int N2 = str2.length;
        int[][] dp = new int[N1][N2];
        dp[0][0] = str1[0] == str2[0] ? 0 : 1;
        // 第一行
        for (int j = 1; j < N2; j++) {
            dp[0][j] = str1[0] == str2[j] ? j : dp[0][j - 1] + 1;
        }
        // 第一列
        for (int i = 1; i < N1; i++) {
            dp[i][0] = str1[i] == str2[0] ? i
                    : dp[i - 1][0] + 1;
        }

        // 从第一行第一列开始计算
        for (int index1 = 1; index1 < str1.length; index1++) {
            for (int index2 = 1; index2 < str2.length; index2++) {
                // 普遍位置填充
                if (str1[index1] == str2[index2]) {
                    dp[index1][index2] = dp[index1 - 1][index2 - 1];
                } else {
                    // 不相等
                    dp[index1][index2] = dp[index1 - 1][index2] + 1;
                    dp[index1][index2] = Math.min(dp[index1][index2], dp[index1][index2 - 1] + 1);
                    dp[index1][index2] = Math.min(dp[index1][index2], dp[index1 - 1][index2 - 1] + 1);
                }
            }
        }
        return dp[N1 - 1][N2 - 1];
    }
}
