package top100;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 */
public class Code073 {
    public void setZeroes(int[][] matrix) {
        // 使用队列存放所有初始位置是0的元素
        // 队列存放0位置的行与列
        Queue<int[]> queue = new LinkedBlockingDeque<>();
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int rowIndex = poll[0];
            int colIndex = poll[1];
            // 将当前行的所有元素置为0
            for (int j = 0; j < col; j++) {
                matrix[rowIndex][j] = 0;
            }
            // 将当前列的所有元素置为0
            for (int i = 0; i < row; i++) {
                matrix[i][colIndex] = 0;
            }
        }
    }
}
