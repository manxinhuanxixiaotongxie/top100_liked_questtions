package newer20;

/**
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 如果存在一个整数 x 使得 n == 2^x ，则认为 n 是 2 的幂次方。
 */
public class Code231 {
    /**
     * 判断一个数是否是2的多少次幂
     * <p>
     * 最后一个1出现的数是不是与当前数相等
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if (n == 0 || n == Integer.MIN_VALUE) {
            return false;
        }
        return n == (n & -n);
    }

    public boolean isPowerOfTwo2(int n) {
        if (n == 0 || n == Integer.MIN_VALUE) {
            return false;
        }
        return n == (n & (~n + 1));
    }
}
