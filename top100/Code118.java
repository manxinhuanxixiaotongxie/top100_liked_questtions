package top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 杨辉三角
 *
 */
public class Code118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; i++) {
            list.add(process(i));
        }
        return list;
    }

    /**
     * Pascal's Triangle
     */
    public List<Integer> process(int rowIndex) {
        if (rowIndex == 0) {
            return Arrays.asList(1);
        }
        List<Integer> lastRow = process(rowIndex - 1);
        // rowIndex = 3
        List<Integer> ans = new ArrayList<>();
        ans.add(lastRow.get(0));
        for (int i = 1; i < rowIndex; i++) {
            ans.add(lastRow.get(i - 1) + lastRow.get(i));
        }
        ans.add(lastRow.get(rowIndex - 1));
        return ans;
    }


    /**
     * 改动态规划
     */

    public List<List<Integer>> generate2(int numRows) {
        List<Integer>[] dp = new List[numRows];
        dp[0] = Arrays.asList(1);
        for (int i = 1; i < numRows; i++) {
            List<Integer> lastRow = dp[i - 1];
            List<Integer> ans = new ArrayList<>();
            ans.add(lastRow.get(0));
            for (int j = 1; j < i; j++) {
                ans.add(lastRow.get(j - 1) + lastRow.get(j));
            }
            ans.add(lastRow.get(i - 1));
            dp[i] = ans;
        }
        return Arrays.asList(dp);
    }

    public List<List<Integer>> generate3(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(Arrays.asList(1));
        for (int i = 1; i < numRows; i++) {
            // 当前行怎么填
            // 第一个数字是1  最后一个数字是1
            List<Integer> cur = new ArrayList<>();
            // 第i行需要填写的数字是 i -1 个
            cur.add(1);
            for (int j = 1; j < i; j++) {
                cur.add((ans.get((i - 1)).get(j - 1)) + ans.get(i - 1).get(j));
            }
            cur.add(1);
            ans.add(cur);
        }
        return ans;
    }
}
