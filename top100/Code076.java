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

    public static void main(String[] args) {
        Code076 code076 = new Code076();
        String s = "cabwefgewcwaefgcf";
        String t = "cae";
        String result = code076.minWindow(s, t);
        System.out.println(result); // 输出 "BANC"
    }
}
