package newer20;

/**
 * 两个整数相加
 * 顺便介绍一种比较高效的解法
 * 使用二进制
 */
public class Code2235 {
    // 使用运算符
    public int sum1(int num1, int num2) {
        return num1 + num2;
    }

    // 不使用运算符

    /**
     * 该方法实现了两个数相加不使用加号运算符 计算两个整数之和
     * 核心思想是利用二进制的无进位加法和进位来模拟加法过程
     *
     * 1,。无进位相加 其实就是两个数进行异或
     * 2.进位信息 (nums1 & nums2) << 1计算哪些位会产生进位 与运算后左移一位
     * 只有两个数位数都是1才有可能产生进位
     * 3.把上一步的无进位和作为新的num1 进位信息作为新的nums2 直到进位信息为0
     *
     * 7: 0111
     * 14: 1110
     * 第一次：无进位和 1001（9），进位 1100（12）
     * 第二次：无进位和 0101（5），进位 10000（16）
     * 第三次：无进位和 10101（21），进位 0，结束。
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
