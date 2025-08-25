public class Code074 {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 整体单调递增
        int row = matrix.length;
        int col = matrix[0].length;
        int rowIndex = 0;
        while (rowIndex < row && matrix[rowIndex][col - 1] < target) {
            rowIndex++;
        }
        if (rowIndex == row) {
            return false;
        }
        // 在当前行进行二分查找
        int left = 0;
        int right = col - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[rowIndex][mid] == target) {
                return true;
            } else if (matrix[rowIndex][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
