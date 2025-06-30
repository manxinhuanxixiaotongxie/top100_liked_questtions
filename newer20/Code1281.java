package newer20;

/**
 * 给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
 * 1 <= n <= 10^5
 */
public class Code1281 {
    public int subtractProductAndSum(int n) {
        int bit = getBit(n);
        int mul = 1;
        int sum = 0;
        // 109
        while (bit > 0) {
            int pow = (int) Math.pow(10, bit - 1);
            int curNum = n / pow;
            mul *= curNum;
            sum += curNum;
            n -= pow * curNum;
            bit--;
        }

        return mul - sum;
    }

    /**
     * 这个函数的含义是 给定一个整数n 获取整数n的长度
     * 假设n= 109 那么长度就是3
     *
     * @param n
     * @return
     */
    public int getBit(int n) {
        int res = 0;
        while (n > 0) {
            res++;
            n /= 10;
        }
        return res;
    }

    public int subtractProductAndSum2(int n) {

        int mul = 1;
        int sum = 0;
        // 109
        while (n > 0) {
            // 获取最后一个数字
            int lastNum = n % 10;
            mul *= lastNum;
            sum += lastNum;
            n /= 10;
        }

        return mul - sum;
    }

}
