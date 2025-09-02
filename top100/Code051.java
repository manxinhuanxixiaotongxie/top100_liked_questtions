package top100;

import java.util.ArrayList;
import java.util.List;

/**
 * N皇后问题
 *
 */
public class Code051 {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        int[] choose = new int[n];
        process(n, 0, choose, ans);
        return ans;
    }

    /**
     * 这个函数的含义：
     * 从row开始进行选择皇后的位置
     * 其中参数的含义是：n是总共有多少行需要进行选择，row是当前正在选择第几行，choose[row]含义是 在row行皇后放置的位置是choose[row]列
     * cur是皇后已经放置好的结果
     * ans是最终放置方案的集合
     *
     * @param n
     * @param row
     * @param choose
     * @param ans
     */
    public void process(int n, int row, int[] choose, List<List<String>> ans) {
        if (row == n) {
            // 说明已经放置完毕
            List<String> cur = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (choose[i] == j) {
                        sb.append('Q');
                    } else {
                        sb.append('.');
                    }
                }
                cur.add(sb.toString());
            }
            ans.add(cur);
        } else {
            // 当前位置是否可以进行放置皇后
            for (int col = 0; col < n; col++) {
                if (isValid(row, col, choose)) {
                    // 当前位置可以放置皇后
                    choose[row] = col;
                    process(n, row + 1, choose, ans);
                    choose[row] = 0;
                }
            }
        }
    }

    public boolean isValid(int row, int col, int[] choose) {
        for (int i = 0; i < row; i++) {
            // 不能同列
            if (choose[i] == col) {
                return false;
            }
        }
        // 不能同对角线
        // 当前的行是row 当前的列是col
        // 那么就是前面的某个位置的行r 某个位置的列c 不能放皇后 Math.abs(r-row) == Math.abs(c-col)
        for (int i = 0; i < row; i++) {
            if (Math.abs(choose[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> ans = new ArrayList<>();
        process2(new int[n], 0, (1 << n) - 1, 0, 0, 0, ans);
        return ans;
    }

    public static int process2(int[] choose, int row, int limit, int colLimit, int colLeftLimit, int colRightLimit, List<List<String>> result) {
        if (colLimit == limit) {
            // 说明已经放置完毕
            List<String> cur = new ArrayList<>();
            for (int i = 0; i < choose.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < choose.length; j++) {
                    if (choose[i] == j) {
                        sb.append('Q');
                    } else {
                        sb.append('.');
                    }
                }
                cur.add(sb.toString());
            }
            result.add(cur);
            return 1;
        }
        // 那么能放的位置是 pos = limit & (~(colLimit | colLeftLimit | colRightLimit))
        int pos = limit & (~(colLimit | colLeftLimit | colRightLimit));
        int ans = 0;
        while (pos != 0) {
            int mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            // 当前行怎么放置
            choose[row] = Integer.bitCount(mostRightOne - 1);
            ans += process2(choose, row + 1, limit, colLimit | mostRightOne, (colLeftLimit | mostRightOne) << 1, (colRightLimit | mostRightOne) >>> 1, result);
        }
        return ans;
    }
}
