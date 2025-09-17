package top100;

/**
 * 最长有效括号
 * <p>
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 */
public class Code032 {

    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return 0;
        }

        char[] str = s.toCharArray();
        int ans = 0;
        for (int i = 1; i < str.length; i++) {
            ans = Math.max(ans, process(str, i));
        }
        return ans;
    }

    /**
     * 必须要以index结尾 最长有效括号子串的长度
     *
     * @param str
     * @param index
     * @return
     */
    public int process(char[] str, int index) {

        if (index <= 0) {
            return 0;
        }
        if (index == 1) {
            return str[0] == '(' && str[1] == ')' ? 2 : 0;
        }
        // 普遍位置依赖
        // 如果当前位置)
        int ans = 0;
        if (str[index] == ')') {
            if (str[index - 1] == '(') {
                ans += process(str, index - 2) + 2;
            } else {
                // 前面一个位置是) 只有一种情况
                int lastIndex = process(str, index - 1);
                if (lastIndex > 0 && index - lastIndex - 1 >= 0 && str[index - lastIndex - 1] == '(') {
                    ans += lastIndex + 2 + process(str, index - lastIndex - 2);
                }
            }
        }
        return ans;
    }

    /**
     * 改动态规划
     *
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return 0;
        }

        char[] str = s.toCharArray();

        int N = str.length;
        int[] dp = new int[N];
        dp[1] = str[0] == '(' && str[1] == ')' ? 2 : 0;
        int ans = dp[1];
        for (int index = 2; index < N; index++) {
            if (str[index] == ')') {
                if (str[index - 1] == '(') {
                    dp[index] = dp[index - 2] + 2;
                } else {
                    int lastIndex = dp[index - 1];
                    if (lastIndex > 0 && index - lastIndex - 1 >= 0 && str[index - lastIndex - 1] == '(') {
                        dp[index] = lastIndex + 2 + (index - lastIndex - 2 >= 0 ? dp[index - lastIndex - 2] : 0);
                    }
                }
                ans = Math.max(ans, dp[index]);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Code032 code032 = new Code032();
        String s = "(()())";
        int res = code032.longestValidParentheses(s);
        System.out.println(res);
    }
}
