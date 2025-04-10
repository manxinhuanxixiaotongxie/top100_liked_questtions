package newer20;

public class Code867 {
    public int[][] transpose(int[][] matrix) {
        int[][] ans = new int[matrix[0].length][matrix.length];
        // 第0行变成第0列
        // 第一行变成第一列
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                ans[col][row] = matrix[row][col];
            }
        }
        return ans;
    }
}
