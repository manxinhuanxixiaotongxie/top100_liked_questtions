package newer20;

/**
 * 给你一个正整数 n ，返回 2 和 n 的最小公倍数（正整数）。
 *
 */
public class Code2413 {
    /**
     * 最小偶倍数
     *
     * @param n
     * @return
     */
    public int smallestEvenMultiple(int n) {
        int ans;
        if (n % 2 == 0) {
            ans = n;
        } else {
            ans = n * 2;
        }
        return ans;
    }

    /**
     * n为奇数当前数左移一位 偶数不变
     * @param n
     * @return
     */
    public int smallestEvenMultiple2(int n) {
        return  n << (n & 1);
    }
}
