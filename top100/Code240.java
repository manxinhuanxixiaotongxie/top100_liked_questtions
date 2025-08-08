package top100;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 */
public class Code240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 从右上角出发
        boolean ans = false;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }
        int beginRow = 0;
        int beginCol = matrix[0].length - 1;
        while (beginRow < matrix.length && beginCol >= 0) {
            if (matrix[beginRow][beginCol] == target) {
                ans = true;
                break;
            } else if (matrix[beginRow][beginCol] < target) {
                beginRow++;
            } else {
                beginCol--;
            }
        }

        return ans;
    }
}
