package top100;

/**
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 */
public class Code079 {
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        char[] str = word.toCharArray();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (process(str, board, row, col, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean process(char[] word, char[][] board, int row, int col, int rowIndex, int colIndex, int index) {
        if (index == word.length) {
            return true;
        }
        if (rowIndex >= row || rowIndex < 0 || colIndex >= col || colIndex < 0 || index > word.length) {
            return false;
        }
        char temp = board[rowIndex][colIndex];
        if (temp != word[index]) {
            return false;
        }
        board[rowIndex][colIndex] = '.';
        boolean res = process(word, board, row, col, rowIndex + 1, colIndex, index + 1)
                || process(word, board, row, col, rowIndex - 1, colIndex, index + 1)
                || process(word, board, row, col, rowIndex, colIndex + 1, index + 1)
                || process(word, board, row, col, rowIndex, colIndex - 1, index + 1);
        board[rowIndex][colIndex] = temp;
        return res;
    }

    public static void main(String[] args) {
        Code079 code079 = new Code079();
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        System.out.println(code079.exist(board, word));
    }
}
