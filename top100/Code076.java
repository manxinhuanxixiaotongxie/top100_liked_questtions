package top100;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * <p>
 * <p>
 * 注意：
 * <p>
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 提示：
 * <p>
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 10^5
 * s 和 t 由英文字母组成
 */
public class Code076 {
    public String minWindow(String s, String t) {
        char[] sStr = s.toCharArray();
        char[] tStr = t.toCharArray();
        if (t.length() > s.length()) {
            return "";
        }
        int[] help = new int[128];
        for (int i = 0; i < tStr.length; i++) {
            help[tStr[i] - 'A']++;
        }
        int end = 0;
        String ans = s;
        boolean find = false;
        for (int i = 0; i < s.length(); i++) {
            while (end < sStr.length && !isZero(help)) {
                help[sStr[end] - 'A']--;
                end++;
                if (end == sStr.length) {
                    break;
                }
            }
            if (isZero(help)) {
                ans = s.substring(i, end).length() < ans.length() ? s.substring(i, end) : ans;
                find = true;
            } else {
                continue;
            }
            help[sStr[i] - 'A']++;
        }
        return find ? ans : "";
    }


    public boolean isZero(int[] help) {
        for (int i = 0; i < help.length; i++) {
            if (help[i] > 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 滑动窗口
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow2(String s, String t) {
        char[] sStr = s.toCharArray();
        char[] tStr = t.toCharArray();
        // 不可能存在这样字符子串
        if (t.length() > s.length()) {
            return "";
        }
        int[] help = new int[128];
        for (char c : tStr) {
            help[c - 'A']++;
        }
        int right = 0;
        int len1 = sStr.length;
        int ansLeft = 0;
        int ansRight = len1;
        int len2 = tStr.length;
        boolean find = false;
        for (int i = 0; i < len1; i++) {
            // 扩大窗口 保证窗口所有字符能够覆盖t
//            while (right < len1 && help[sStr[right] - 'A'] >= 0) {
            while (right < len1 && !check(help)) {
                help[sStr[right++] - 'A']--;
            }
            if (right < len2) {
                continue;
            }
            // 结算
            if (check(help)) {
                find = true;
                if (right - i < ansRight - ansLeft) {
                    ansLeft = i;
                    ansRight = right;
                }
            }
            help[sStr[i] - 'A']++;
        }
        return !find ? "" : s.substring(ansLeft, ansRight);
    }

    /**
     * 滑动窗口的时候 不需要每次都检查窗口是否能够完全覆盖目标字符串
     *
     * 这个思路与030的滑动窗口 思路是一样的 030是定长的滑动窗口
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow3(String s, String t) {
        char[] sStr = s.toCharArray();
        char[] tStr = t.toCharArray();
        // 不可能存在这样字符子串
        if (t.length() > s.length()) {
            return "";
        }
        int[] help = new int[128];
        for (char c : tStr) {
            help[c - 'A']++;
        }
        int right = 0;
        int len1 = sStr.length;
        int ansLeft = 0;
        int ansRight = len1;
        int len2 = tStr.length;
        // 记录的是有效覆盖 而不是字符总数
        int count = len2;
        boolean find = false;
        for (int i = 0; i < len1; i++) {
            // 扩大窗口 保证窗口所有字符能够覆盖t
            while (right < len1 && count > 0) {
                // help[i]含义 正数：代表你还欠这个字符多少个 负数：代表这个字符在窗口里多出来了
                if (help[sStr[right] - 'A'] > 0) {
                    count--;
                }
                help[sStr[right++] - 'A']--;
            }
            if (right < len2) {
                continue;
            }
            // 结算
            if (count == 0) {
                find = true;
                if (right - i < ansRight - ansLeft) {
                    ansLeft = i;
                    ansRight = right;
                }
            }
            help[sStr[i] - 'A']++;
            // count 是一个“欠债计数器”。只有当你填补了还没补上的窟窿时，count 才会减少；只有当你拆了不可或缺的东墙时，count 才会增加。
            if (help[sStr[i] - 'A'] > 0) {
                // 只有当你拿走的这个字符 会导致我重新开始缺钱 count才会增加
                count++;
            }

        }
        return !find ? "" : s.substring(ansLeft, ansRight);
    }

    public boolean check(int[] help) {
        for (int i = 0; i < help.length; i++) {
            if (help[i] > 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Code076 code076 = new Code076();
        String s = "cabwefgewcwaefgcf";
        String t = "cae";
        String result = code076.minWindow(s, t);
        System.out.println(result); // 输出 "BANC"
    }
}
