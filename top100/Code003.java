package top100;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Code003 {
    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
        // 滑动窗口
        int n = s.length();
        int end = 0;
        Set<Character> set = new HashSet<>();
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            while (end < n && !set.contains(s.charAt(end))) {
                set.add(s.charAt(end));
                end++;
            }
            maxLength = Math.max(maxLength, end - i);
            if (end == i) {
                end = i + 1;
            }
            set.remove(s.charAt(i));
        }
        return maxLength;
    }

    /**
     * 贪心
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 必须以i结尾
        // 前一个位置最长不重复子串的长度
        int pre = 1;
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        map.put(chars[0], 0);
        int N = s.length();
        int ans = 1;
        for (int i = 1; i < N; i++) {
            // 必须以i结尾
            if (!map.containsKey(chars[i])) {
                pre = pre + 1;
                ans = Math.max(pre, ans);
            } else {
                // 当前字符已经存在
                if (i - map.get(chars[i]) > pre) {
                    pre = pre + 1;
                    ans = Math.max(pre, ans);
                } else {
                    // 当前字符已经存在，并且在当前最长不重复子串中
                    pre = i - map.get(chars[i]);
                }
            }
            map.put(chars[i], i);
        }
        return ans;
    }
}
