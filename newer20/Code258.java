package newer20;

/**
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 * <p>
 * 进阶：你可以不使用循环或者递归，在 O(1) 时间复杂度内解决这个问题吗？
 */
public class Code258 {
    public static int addDigits(int num) {
        String str = String.valueOf(num);
        while (Integer.parseInt(str) >= 10) {
            Integer temp = 0;
            for (int i = 0; i < str.length(); i++) {
                temp += str.charAt(i) - '0';
            }
            str = String.valueOf(temp);
        }
        return Integer.parseInt(str);
    }

    public static int addDigits3(int num) {

        while (num >= 10) {
            int temp = 0;
            while (num > 0) {
                temp += num % 10;
                num /= 10;
            }
            num = temp;
        }
        return num;
    }

    /**
     * 打表
     * <p>
     * 打印的顺序是 9个数一组
     *
     * @param num
     * @return
     */
    public static int addDigits2(int num) {
        if (num <= 0) {
            return 0;
        }
        if (num % 9 == 0) {
            return 9;
        }
        return num % 9;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println("当前的数是:" + i + "," + "转化之后的数是" + addDigits(i));
        }
    }
}
