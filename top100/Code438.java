package top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 */
public class Code438 {
    /**
     * 滑动窗口 滑动窗口的长度为P的长度
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        if (p.length() > s.length()) {
            return new ArrayList<>();
        }
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        int[] pCount = new int[26];
        for (int i = 0; i < pChars.length; i++) {
            pCount[pChars[i] - 'a']++;
        }
        int pLength = pChars.length;
        int[] sCount = new int[26];
        for (int i = 0; i < pLength - 1; i++) {
            sCount[sChars[i] - 'a']++;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = pLength - 1; i < sChars.length; i++) {
            sCount[sChars[i] - 'a']++;
            if (Arrays.equals(pCount, sCount)) {
                ans.add(i - pLength + 1);
            }
            sCount[sChars[i - pLength + 1] - 'a']--;
        }
        return ans;
    }
}
