package top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 */
public class Code049 {
    /**
     * 暴力解法
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        boolean[] visited = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            if (visited[i]) {
                continue;
            }
            List<String> list = new ArrayList<>();
            list.add(strs[i]);
            visited[i] = true;
            for (int j = i + 1; j < strs.length; j++) {
                String next = strs[j];
                // 判断next与strs[i]是否是字母异位词
                if (isSameWord(strs[i], next) && !visited[j]) {
                    list.add(next);
                    visited[j] = true;
                }
            }
            res.add(list);
        }

        return res;
    }


    private boolean isSameWord(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 == null || str2 == null) {
            return false;
        }
        if (str1.length() != str2.length()) {
            return false;
        }
        // 拆分字符串
        int[] index = new int[26];
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        for (int i = 0; i < s1.length; i++) {
            index[s1[i] - 'a']++;
            index[s2[i] - 'a']--;
        }
        for (int i = 0; i < index.length; i++) {
            if (index[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 加速
     *
     * 使用HashMap + 排序分组的方式
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            map.computeIfAbsent(new String(arr), _ -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
