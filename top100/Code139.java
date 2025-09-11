package top100;

import java.util.HashSet;
import java.util.List;

/**
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典，判定 s 是否可以由空格拆分为一个或多个在字典中出现的单词。
 * 注意：拆分时可以重复使用字典中的单词。 不要求字典中出现的单词全部都使用。
 */
public class Code139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> dicts = new HashSet<>(wordDict);
        return process(s, 0, dicts);
    }


    public boolean process(String s, int index, HashSet<String> dicts) {
        if (index == s.length()) return true;
        for (int i = index; i < s.length(); i++) {
            // 暴力遍历从index到s.length()的所有子串
            if (dicts.contains(s.substring(index, i + 1)) && process(s, i + 1, dicts)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 改成动态规划
     *
     */
    public boolean wordBreak2(String s, List<String> worDict) {
        HashSet<String> dicts = new HashSet<>(worDict);
        int N = s.length();
        boolean[] dp = new boolean[N + 1];
        dp[N] = true;
        for (int index = N - 1; index >= 0; index--) {
            for (int i = index; i < s.length(); i++) {
                // 暴力遍历从index到s.length()的所有子串
                if (dicts.contains(s.substring(index, i + 1)) && dp[i + 1]) {
                    dp[index] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    /**
     *
     * 利用前缀树的思想进行优化
     *
     * @param s
     * @param worDict
     * @return
     */
    public boolean wordBreak3(String s, List<String> worDict) {
        // 构建前缀树
        TrieTree trieTree = new TrieTree();
        for (String str : worDict) {
            trieTree.insert(str);
        }

        int N = s.length();
        boolean[] dp = new boolean[N + 1];
        dp[N] = true;
        for (int index = N - 1; index >= 0; index--) {
            for (int i = index; i < s.length(); i++) {
                // 暴力遍历从index到s.length()的所有子串
                if (trieTree.contains(s.substring(index, i + 1)) && dp[i + 1]) {
                    dp[index] = true;
                    break;
                }
            }
        }
        return dp[0];
    }


    /**
     * 题目已经明确了字符串仅有小写字母构成
     * 使用数组实现前缀树
     * 每个前缀树接口包含一个26长度的数组指向下一个路径
     *
     */
    class TrieTreeNode {
        TrieTreeNode[] next;
        char value;
        int end;

        // 经典的前缀树节点 还需要pass用于模糊搜索 但是这道题用不上
        TrieTreeNode(char value) {
            this.value = value;
            next = new TrieTreeNode[26];
            end = 0;
        }
    }

    class TrieTree {
        // 根节点
        TrieTreeNode root;


        TrieTree() {
            root = new TrieTreeNode('\0');
        }

        public void insert(String s) {
            TrieTreeNode cur = root;
            char[] str = s.toCharArray();
            for (char c : str) {
                if (cur.next[c - 'a'] == null) {
                    cur.next[c - 'a'] = new TrieTreeNode(c);
                }
                cur = cur.next[c - 'a'];
            }
            cur.end++;
        }

        public boolean contains(String s) {
            char[] str = s.toCharArray();
            TrieTreeNode cur = root;
            for (char c : str) {
                if (cur.next[c - 'a'] == null) {
                    return false;
                }
                cur = cur.next[c - 'a'];
            }
            return cur.end != 0;
        }


    }

    /**
     * 这个尝试失败了
     */
//    public boolean process(String s,List<String> wordDict,int index) {
//        if (s.length() == 0) {
//            return true;
//        }
//        if (index == wordDict.size()) {
//            return false;
//        }
//
//        boolean ans = false;
//        for (int i = 0;i < wordDict.size();i++) {
//            // 当前位置进行选择
//            if (wordDict.contains(s.substring(index, index + i))) {}
//        }
//
//        return ans;
//    }
}
