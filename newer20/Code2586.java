package newer20;

import java.util.HashSet;

/**
 * 你一个下标从 0 开始的字符串数组 words 和两个整数：left 和 right 。
 *
 * 如果字符串以元音字母开头并以元音字母结尾，那么该字符串就是一个 元音字符串 ，其中元音字母是 'a'、'e'、'i'、'o'、'u' 。
 *
 * 返回 words[i] 是元音字符串的数目，其中 i 在闭区间 [left, right] 内。
 *
 *
 */
public class Code2586 {
    public int vowelStrings(String[] words, int left, int right) {
        int ans = 0;
        HashSet<Character> characters = new HashSet<>();
        characters.add('a');
        characters.add('e');
        characters.add('i');
        characters.add('o');
        characters.add('u');
        while (left <= right) {
            String cur = words[left];
            if (characters.contains(cur.charAt(0)) && characters.contains(cur.charAt(cur.length() - 1))){
                ans++;
            }
            left++;
        }
        return ans;
    }
}
