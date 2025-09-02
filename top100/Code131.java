package top100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 */
public class Code131 {
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        char[] str = s.toCharArray();
        process(str, 0, new LinkedList<>(), ans);
        return ans;
    }

    public void process(char[] str, int index, LinkedList<String> path, List<List<String>> ans) {
        if (index == str.length) {
            ans.add(new ArrayList<>(path));
        }else {
            for (int i = index; i < str.length; i++) {
                if (isPalindrome(str,index,i)) {
                    path.add(new String(str,index,i-index+1));
                    process(str,i+1,path,ans);
                    path.removeLast();
                }
            }
        }
    }

    public boolean isPalindrome(char[] str, int l, int r) {
        while (l < r) {
            if (str[l] != str[r]) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
