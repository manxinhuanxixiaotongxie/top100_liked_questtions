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
}
