package newer20;

/**
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3^x
 */
public class Code326 {

    public boolean isPowerOfThree(int n) {
        while (n != 0 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    /**
     * 这个数是整数范围内 3的幂次方的最大值
     * 如果一个数是3的幂次方，那么它一定能被 3^19 整除
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree2(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
