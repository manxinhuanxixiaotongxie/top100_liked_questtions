package top100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 */
public class Code054 {
    public List<Integer> spiralOrder(int[][] matrix) {
        // 分圈结构
        int row = matrix.length;
        int col = matrix[0].length;
        List<Integer> ans = new ArrayList<>();

        int rowStart = 0;
        int colStart = 0;
        int rowEnd = row - 1;
        int colEnd = col - 1;
        while (rowStart <= rowEnd && colStart <= colEnd) {
            printCycle(matrix, ans, rowStart++, colStart++, rowEnd--, colEnd--);
        }

        return ans;
    }

    public void printCycle(int[][] matrix, List<Integer> ans, int rowStrart, int colStrart, int rowEnd, int colEnd) {
        // 讨论极端情况
        if (rowStrart == rowEnd) {
            // 只有一行
            for (int i = colStrart; i <= colEnd; i++) {
                ans.add(matrix[rowStrart][i]);
            }
        }
        // 只有一列
        else if (colStrart == colEnd) {
            for (int i = rowStrart; i <= rowEnd; i++) {
                ans.add(matrix[i][colStrart]);
            }
        } else {
            // 有多行 多列
            // 从rowStart整行但是不包括最后一个的位置
            for (int i = colStrart; i < colEnd; i++) {
                ans.add(matrix[rowStrart][i]);
            }
            // 右侧的列
            for (int i = rowStrart; i < rowEnd; i++) {
                ans.add(matrix[i][colEnd]);
            }
            // 底部的行
            for (int i = colEnd; i > colStrart; i--) {
                ans.add(matrix[rowEnd][i]);
            }
            // 左侧的列
            for (int i = rowEnd; i > rowStrart; i--) {
                ans.add(matrix[i][colStrart]);
            }

        }
    }
}
