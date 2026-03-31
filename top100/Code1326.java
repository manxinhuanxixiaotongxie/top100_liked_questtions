package top100;

/**
 * 在 x 轴上有一个一维的花园。花园长度为 n，从点 0 开始，到点 n 结束。
 * <p>
 * 花园里总共有 n + 1 个水龙头，分别位于 [0, 1, ..., n] 。
 * <p>
 * 给你一个整数 n 和一个长度为 n + 1 的整数数组 ranges ，其中 ranges[i] （下标从 0 开始）表示：如果打开点 i 处的水龙头，
 * 可以灌溉的区域为 [i -  ranges[i], i + ranges[i]] 。
 * <p>
 * 请你返回可以灌溉整个花园的 最少水龙头数目 。如果花园始终存在无法灌溉到的地方，请你返回 -1 。
 *
 */
public class Code1326 {
    /**
     * 无法Ac 待分析
     *
     * 这样计算只是计算了灌溉单边的最远距离 但是灌溉范围是双边的
     *
     * 方法1 第一个循环执行过程：
     * i
     * i > mx?
     * mx
     * 0
     * 0 > 0 No
     * max(0, 0+0) = 0
     * 1
     * 1 > 0 Yes → 直接 return -1 ❌
     *
     * 第一个循环在 i=1 时，因为 mx=0，判断 i > mx 直接返回 -1，但实际上位置 3 的水龙头完全覆盖了 [0,6]，根本不会无法灌溉。
     *
     * 根本原因
     * 方法1 的循环等价于：每个位置 i 只能用自己的水龙头向右延伸，但水龙头是双向覆盖的，一个中心在 j > i 的水龙头，只要 j - ranges[j] <= i，就能覆盖位置 i。
     * 方法2 的修正思路：预处理 rightMost 数组，把每个水龙头的覆盖转化为"从左端点出发能到达的最右点"：
     *
     * @param n
     * @param ranges
     * @return
     */
    public int minTaps(int n, int[] ranges) {
        int mx = 0;
        for (int i = 0; i <= n; i++) {
            if (i > mx) return -1;
            mx = Math.max(mx, i + ranges[i]);
        }

        int ans = 0;
        int mostRight = 0;
        int nextMostRight = 0;
        for (int i = 0; i < n; i++) {
            nextMostRight = Math.max(nextMostRight, i + ranges[i]);
            if (i == mostRight) {
                ans++;
                mostRight = nextMostRight;
            }
        }
        return ans;
    }

    public int minTaps2(int n, int[] ranges) {
        int[] rightMost = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int r = ranges[i];
            int left = Math.max(i - r, 0);
            rightMost[left] =  Math.max(rightMost[left], i + r);
        }

        int ans = 0;
        int mostRight = 0;
        int nextMostRight = 0;
        for (int i = 0; i < n; i++) {
//            nextMostRight = Math.max(nextMostRight, i + rightMost[i]);
            nextMostRight = Math.max(nextMostRight, rightMost[i]);
            if (i == mostRight) {
                if (i == nextMostRight) {
                    return -1;
                }
                ans++;
                mostRight = nextMostRight;
            }
        }
        return ans;

    }
}
