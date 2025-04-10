package newer20;

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
}
