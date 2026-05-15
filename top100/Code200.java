package top100;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 岛问题解决连通性
 *
 */
public class Code200 {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    affect(grid, i, j);
                }
            }
        }
        return res;

    }

    private void affect(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length)
            return;
        if (grid[x][y] == '1') {
            grid[x][y] = 2;
            affect(grid, x - 1, y);
            affect(grid, x + 1, y);
            affect(grid, x, y - 1);
            affect(grid, x, y + 1);
        }
    }
    /**
     * 并查集思路
     *
     * @param grid
     * @return
     */
    public int numIslands2(char[][] grid) {
        UnionSet unionSet = new UnionSet(grid);
        int row = grid.length;
        int col = grid[0].length;
        // 先处理第0行和第0列
        for (int i = 1; i < col; i++) {
            if (grid[0][i] == '1' && grid[0][i - 1] == '1') {
                unionSet.union(0, i, 0, i - 1);
            }
        }
        // 第0列
        for (int i = 1; i < row; i++) {
            if (grid[i][0] == '1' && grid[i - 1][0] == '1') {
                unionSet.union(i, 0, i - 1, 0);
            }
        }
        // 普遍位置
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (grid[i][j] == '1') {
                    // 上
                    if (grid[i - 1][j] == '1') {
                        unionSet.union(i, j, i - 1, j);
                    }
                    // 左
                    if (grid[i][j - 1] == '1') {
                        unionSet.union(i, j, i, j - 1);
                    }
                }
            }
        }
        return unionSet.size;
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        Code200 code200 = new Code200();
        int result = code200.numIslands(grid);
        System.out.println("Number of islands: " + result); // 输出: 3
    }

    class UnionSet {
        int col;
        int size;
        int limit;
        Map<Integer, Integer> fatherMap;
        Map<Integer, Integer> sizeMap;

        UnionSet(char[][] grid) {
            this.col = grid[0].length;
            fatherMap = new HashMap<Integer, Integer>();
            sizeMap = new HashMap<Integer, Integer>();
            size = 0;
            limit = grid.length * grid[0].length;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        int index = getIndex(i, j);
                        fatherMap.put(index, index);
                        sizeMap.put(index, 1);
                        size += 1;
                    }
                }
            }
        }


        public void union(int i1, int j1, int i2, int j2) {
            int father1 = findFather(i1, j1);
            int father2 = findFather(i2, j2);
            if (father1 != father2) {
                if (sizeMap.get(father1) >= sizeMap.get(father2)) {
                    sizeMap.put(father1, sizeMap.get(father1) + sizeMap.get(father2));
                    fatherMap.put(father2, father1);
                } else {
                    sizeMap.put(father2, sizeMap.get(father1) + sizeMap.get(father2));
                    fatherMap.put(father1, father2);
                }
                size -= 1; // 合并后岛屿数量减少
            }
        }

        public int findFather(int i, int j) {
            int[] help = new int[limit];
            int helpIndex = 0;
            int index = getIndex(i, j);
            while (index != fatherMap.get(index)) {
                help[helpIndex++] = index;
                index = fatherMap.get(index);
            }
            while (helpIndex > 0) {
                fatherMap.put(help[--helpIndex], index);
            }
            return fatherMap.get(index);
        }


        private int getIndex(int i, int j) {
            return i * col + j;
        }
    }
}
