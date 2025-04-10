package newer20;

/**
 * 两个整数相加
 */
public class Code2235 {
    // 使用运算符
    public int sum1(int num1, int num2) {
        return num1 + num2;
    }

    // 不使用运算符

    /**
     * 以7 + 14为例   答案应该是21
     * 7的二进制是 1 + 2 + 4       0111
     * <p>
     * 14的二进制是 8 + 4 + 2      1110
     * <p>
     * <p>
     * 21的二进制是：16+4 + 1      10101
     *
     * @param num1
     * @param num2
     * @return
     */
    public int sum2(int num1, int num2) {

        int ans = num1;

        while (num2 != 0) {
            // 无进位相加
            ans = num1 ^ num2;
            // 进位信息
            num2 = (num1 & num2) << 1;
            num1 = ans;
        }
        return ans;
    }
}
