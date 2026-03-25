package top100;

public class Code136 {
    /**
     * 利用异或的特性 相同为0 不同为1
     * <p>
     * 出现两次的数  异或的结果为0
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }

    /**
     * 补充题目：一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数？
     */
    public int singleNumber2(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
        }
        // 那么此时ans就是那两个奇数异或的结果
        // 异或的特性 相同为0  不同为1
        // 找到最后一位数
        int lastOne = ans & (-ans);
        int ans1 = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & lastOne) == 0) {
                ans1 ^= nums[i];
            }
        }
        ans ^= ans1;
        // ans就是另外一个数
        return ans;
    }

    /**
     * 补充题目 一个数组中有一种数出现K次，其他数都出现M次，M>1,K>1,找到出现了K次的数，
     */
    public int singleNumber2(int[] nums, int M) {
        int[] help = new int[32];
        for (int num : nums) {
            for (int j = 0; j < 32; j++) {
                if (((num >> j) & 1) != 0) {
                    help[j]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (help[i] % M != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }
}
