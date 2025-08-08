package top100;

/**
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * <p>
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 */
public class Code048 {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        // 分圈结构 顺时针旋转90度
        int rowStart = 0;
        int colStart = 0;
        int rowEnd = matrix.length - 1;
        int colEnd = matrix[0].length - 1;
        while (rowStart < rowEnd && colStart < colEnd) {
            printCycle(matrix, rowStart++, colStart++, rowEnd--, colEnd--);
        }
    }

    // 分圈交换
    public void printCycle(int[][] matrix, int rowStart, int colStart, int rowEnd, int colEnd) {
        // 总共有多少个圈需要进行交换
        int cycle = 0;
        while (cycle < rowEnd - rowStart) {
            // 第一组第一个数 rowStart, colStart    第二组的第一个数 rowStart, colStart + cycle
            // 第一组的第二个数 rowStart  0         第二组的第二个数 rowStart + cycle, colEnd
            int temp = matrix[rowStart][colStart + cycle];
            matrix[rowStart][colStart + cycle] = matrix[rowEnd - cycle][colStart];
            matrix[rowEnd - cycle][colStart] = matrix[rowEnd][colEnd - cycle];
            matrix[rowEnd][colEnd - cycle] = matrix[rowStart + cycle][colEnd];
            matrix[rowStart + cycle][colEnd] = temp;
            cycle++;
        }
    }
}
