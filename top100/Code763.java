package top100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个字符串 s ，请你将 s 分割成尽可能多的片段，并满足每个字母 最多 出现在一个片段中。 返回一个表示每个字符串片段的长度的列表。
 * 注意：划分的结果需要满足 将所有的的划分结果按顺序连接 ，得到的字符串仍然是 s 。
 * 提示：s 仅由小写英文字母组成
 *
 */
public class Code763 {
    /**
     * 使用辅助结构记录每个字母最后一次出现的位置
     *
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        char[] str = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int N = str.length;
        // 记录每个字母最后一次出现的位置
        for (int i = N - 1; i >= 0; i--) {
            if (!map.containsKey(str[i])) {
                map.put(str[i], i);
            }
        }
        int end = 0;
        List<Integer> ans = new ArrayList<>();
        while (end < N) {
            int begin = end;
            int cur = end;
            end = map.get(str[end]);
            while (cur <= end) {
                end = Math.max(end, map.get(str[cur]));
                cur++;
            }
            // 扩大end 结算
            // 记录从cur到end的长度
            ans.add(end - begin + 1);
            end++;
        }

        return ans;

    }

    /**
     * 题目规定s仅由小写英文字母组成，可以使用数组代替map
     * @param s
     * @return
     */
    public List<Integer> partitionLabels2(String s) {
        char[] str = s.toCharArray();
        int[] map = new int[26];
        int N = str.length;
        // 记录每个字母最后一次出现的位置
        for (int i = N - 1; i >= 0; i--) {
            if (map[str[i] - 'a'] == 0) {
                map[str[i] - 'a'] = i;
            }
        }
        int end = 0;
        List<Integer> ans = new ArrayList<>();
        while (end < N) {
            int begin = end;
            int cur = end;
            end = map[str[end] - 'a'];
            while (cur <= end) {
                end = Math.max(end, map[str[cur] - 'a']);
                cur++;
            }
            // 扩大end 结算
            // 记录从cur到end的长度
            ans.add(end - begin + 1);
            end++;
        }

        return ans;

    }
}
