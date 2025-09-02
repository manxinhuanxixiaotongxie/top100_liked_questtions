package top100;

import java.util.ArrayList;
import java.util.List;

public class Code022 {
    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        char[] path = new char[n << 1];
        List<String> ans = new ArrayList<>();
        process(path, 0, 0, n, ans);
        return ans;
    }

    /**
     * 这个递归函数的含义是从0-path.length-1位置上，生成所有可能的括号组合
     * leftMinusRight: 当前已经生成的左括号数量减去右括号数量
     * leftRest: 还剩多少左括号没有使用
     *
     * @param path
     * @param index
     * @param leftMinusRight
     * @param leftRest
     * @param ans
     */
    public static void process(char[] path, int index, int leftMinusRight, int leftRest, List<String> ans) {
        if (index == path.length) {
            ans.add(String.valueOf(path));
        } else {
            // 暴力递归的时候 只要还有左括号就可以放左括号
            if (leftRest > 0) {
                path[index] = '(';
                process(path, index + 1, leftMinusRight + 1, leftRest - 1, ans);
            }
            // 放有括号的条件是 当前已经生成的左括号数量大于右括号数量
            if (leftMinusRight > 0) {
                path[index] = ')';
                process(path, index + 1, leftMinusRight - 1, leftRest, ans);
            }
        }
    }

}
