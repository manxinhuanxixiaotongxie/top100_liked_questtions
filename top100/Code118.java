package top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<Integer> process = process(rowIndex - 1);
        // rowIndex = 3
        List<Integer> ans = new ArrayList<>();
        ans.add(process.get(0));
        for (int i = 1; i < rowIndex; i++) {
            ans.add(process.get(i - 1) + process.get(i));
        }
        ans.add(process.get(rowIndex - 1));
        return ans;
    }


    /**
     * 改动态规划
     */

    public List<List<Integer>> generate2(int numRows) {
        List<Integer>[] dp = new List[numRows];
        dp[0] = Arrays.asList(1);
        for (int i = 1; i < numRows; i++) {
            List<Integer> process = dp[i - 1];
            List<Integer> ans = new ArrayList<>();
            ans.add(process.get(0));
            for (int j = 1; j < i; j++) {
                ans.add(process.get(j - 1) + process.get(j));
            }
            ans.add(process.get(i - 1));
            dp[i] = ans;
        }
        return Arrays.asList(dp);
    }
}
