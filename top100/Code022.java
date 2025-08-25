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

    // 依次在path上填写决定
    // ( ( ) ) ( )....
    // 0 1 2 3 4 5
    // path[0...index-1]决定已经做完了
    // index位置上，( )
    public static void process(char[] path, int index, int leftMinusRight, int leftRest, List<String> ans) {
        if (index == path.length) {
            ans.add(String.valueOf(path));
        } else {
            if (leftRest > 0) {
                path[index] = '(';
                process(path, index + 1, leftMinusRight + 1, leftRest - 1, ans);
            }
            if (leftMinusRight > 0) {
                path[index] = ')';
                process(path, index + 1, leftMinusRight - 1, leftRest, ans);
            }
        }
    }

    private boolean isValid(char[] path) {
        path[0] = '(';
        path[path.length - 1] = ')';
        int count = 0;
        for (char cha : path) {
            if (cha == '(') {
                count++;
            } else {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;

    }
}
