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

    /**
     * 空间压缩技巧
     * <p>
     * 每一行都只依赖上一行的数据
     * 依赖的位置如下：
     * 第一个位置 左上角的位置
     * 第二个位置：上顶点的位置
     * <p>
     * 采用滚动数组的方式进行空间压缩
     *
     * @param text1
     * @param text2
     * @return
     */
    public int minDistance3(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        if (s1.length == 0) return s2.length;
        if (s2.length == 0) return s1.length;
        int N = text2.length();
        int[] dp = new int[N];
        // 滚动数组更新的方式
        // 先填写第一行的值
        dp[0] = s1[0] == s2[0] ? 0 : 1;
        for (int i = 1; i < N; i++) {
            dp[i] = s1[0] == s2[i] ? i : dp[i - 1] + 1;
        }
        // 循环次数是不会变的
        for (int index1 = 1; index1 < s1.length; index1++) {
            // 开始滚动更新数组
            // 左上角的数
            int leftTop = dp[0];
            dp[0] = s1[index1] == s2[0] ? index1 : dp[0] + 1;
            for (int index2 = 1; index2 < N; index2++) {
                if (s1[index1] == s2[index2]) {
                    int temp = dp[index2];
                    dp[index2] = leftTop;
                    leftTop = temp;
                } else {
                    // 需要的维护是 上面的位置
                    int ans = dp[index2] + 1;
                    // 左边的位置
                    ans = Math.min(ans, dp[index2 - 1] + 1);

                    // 左上角的位置
                    ans = Math.min(ans, leftTop + 1);
                    // 更新左上角的位置
                    leftTop = dp[index2];
                    dp[index2] = ans;
                }
            }
        }

        return dp[N - 1];
    }
}
