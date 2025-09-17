package top100;

public class Code005 {
    /**
     * 最长回文子串
     * <p>
     * 暴力递归
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        char[] str = s.toCharArray();
        return process(str, 0, str.length - 1);
    }

    /**
     * 含义：
     * left right范围上最长的回文子串
     *
     * @return
     */
    public String process(char[] str, int left, int right) {
        if (left > right) return "";
        if (left == right) {
            return str[left] + "";
        }
        // 如果left到right不是回文
        if (isPalindrome(str, left, right)) {
            return new String(str, left, right - left + 1);
        } else {
            String p1 = process(str, left + 1, right);
            String p2 = process(str, left, right - 1);
            return p1.length() >= p2.length() ? p1 : p2;
        }

    }

    public boolean isPalindrome(char[] str, int left, int right) {
        while (left < right) {
            if (str[left] != str[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 改动态规划
     * <p>
     * 这个动态规划无法完全AC 但是是对的 需要采用更简洁的写法
     * 避免使用String进行存储
     *
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        char[] str = s.toCharArray();
        String[][] dp = new String[str.length][str.length];
        //left做行 right做列
        // 依赖的位置是下面行   右边列的位置
        // 从最后一行开始填写
        // 对角线位置 就是字符本身
        for (int i = 0; i < str.length; i++) {
            dp[i][i] = str[i] + "";
        }
        // 倒数第二行位置开始填写
        // 从右边位置开始填写
        for (int left = str.length - 2; left >= 0; left--) {
            for (int right = left + 1; right < str.length; right++) {
                if (isPalindrome(str, left, right)) {
                    dp[left][right] = new String(str, left, right - left + 1);
                } else {
                    String p1 = dp[left + 1][right];
                    String p2 = dp[left][right - 1];
                    dp[left][right] = p1.length() >= p2.length() ? p1 : p2;
                }
            }
        }
        return dp[0][str.length - 1];
    }

    /**
     * 中心扩大的方式
     * <p>
     * 回文子串有可能是是技术长度 也有可能是偶数长度 举个例子
     * ab a b a  这个回文长度就是奇数
     * <p>
     * abccba 这个回文长度就是偶数
     *
     * @param s
     * @return
     */
    public String longestPalindrome3(String s) {
        // 从0到N-1位置都都尝试中心扩大的方式
        // 第一次采用奇数的方式
        int left = 0;
        int right = 0;
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            int curleft = i;
            int curRight = i;
            while (curleft >= 0 && curRight < str.length) {
                if (str[curleft] == str[curRight]) {
                    curleft--;
                    curRight++;
                } else {
                    break;
                }
            }
            if (right - left < curRight - curleft - 2) {
                left = curleft + 1;
                right = curRight - 1;
            }
        }
        // 偶数的方式
        // 使用i i+1位置作为中心进行扩大
        for (int i = 0; i < str.length - 1; i++) {
            int curLeft = i;
            int curRight = i + 1;
            while (curLeft >= 0 && curRight < str.length) {
                if (str[curLeft] == str[curRight]) {
                    curLeft--;
                    curRight++;
                } else {
                    break;
                }
            }
            if (right - left < curRight - curLeft - 2) {
                left = curLeft + 1;
                right = curRight - 1;
            }
        }
        return new String(str, left, right - left + 1);
    }

    /**
     * manacher算法
     * <p>
     * manacher算法用于计算最长子串 能够在O（N）的时间复杂度内完成 有很好的性能
     * <p>
     * 1.将数组加工成长度为原始长度2倍的新数组
     * <p>
     * 2.引入三个概念
     * 2.1 第一个概念 回文半径
     * 2.2 第二个概念 回文半径数组
     * 2.3 右边界 当前回文半径下能够达到的右边界
     * <p>
     * 3.列举可能性
     * <p>
     * 1.i关于回文半径的对称点i'的回文半径在L-R范围内  那么i的回文半径就是i'的回文半径
     * 2.i关于回文半径的对称点i'的回文半径刚好等于L 只能暴力扩大
     * 3.i关于回文半径的对称点i'的回文半径超过L  不能扩大
     *
     * @param s
     * @return
     */
    public String longestPalindrome4(String s) {
        // 获得一个两倍长度的数组
        char[] str = s.toCharArray();
        char[] newStr = getString(str);
        // 回文半径中心
        int c = 0;
        // 右边界
        int r = -1;
        // 回文半径数组
        int[] next = new int[newStr.length];
        int maxLen = 0, center = 0;

        for (int i = 0; i < newStr.length; i++) {
            next[i] = r > i ? Math.min(next[2 * c - i], r - i) : 1;
            while (i + next[i] < newStr.length && i - next[i] > -1) {
                if (newStr[i + next[i]] == newStr[i - next[i]]) {
                    next[i]++;
                } else {
                    break;
                }
            }
            if (i + next[i] > r) {
                r = i + next[i];
                c = i;
            }
            if (next[i] - 1 > maxLen) {
                maxLen = next[i] - 1;
                center = i;
            }
        }
        int start = (center - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }


    public char[] getString(char[] str) {
        int N = str.length;
        // 生成一个长度为原始数组长度2倍的新数组
        char[] res = new char[(N << 1) | 1];
        res[0] = '#';
        for (int i = 0; i < str.length; i++) {
            // 对应关系是 str[0]   对应res[1]
            //          str[1]   对应res[3]
            res[i << 1] = '#';
            res[i << 1 | 1] = str[i];
        }
        res[res.length - 1] = '#';

        return res;

    }

    public static void main(String[] args) {
        String s = "cbbd";
        Code005 code005 = new Code005();
        String res = code005.longestPalindrome(s);
        String res2 = code005.longestPalindrome2(s);
        String res3 = code005.longestPalindrome3(s);
        String res4 = code005.longestPalindrome4(s);
        System.out.println(res4);
        System.out.println(res3);
        System.out.println(res2);
        System.out.println(res);
    }
}
