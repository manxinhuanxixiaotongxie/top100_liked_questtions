package newer20;

/**
 * 给你两个整数，n 和 start 。
 * <p>
 * 数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。
 * <p>
 * 请返回 nums 中所有元素按位异或（XOR）后得到的结果。
 */
public class Code1486 {

    /**
     * 创建辅助数组
     *
     * @param n
     * @param start
     * @return
     */
    public int xorOperation(int n, int start) {
        int[] nums = new int[n];
        nums[0] = start;
        int index = 1;
        while(index < n) {
            nums[index] = nums[index-1]+2;
            index++;
        }
        int ans = nums[0];
        for(int i = 1; i < nums.length;i++) {
            ans ^= nums[i];
        }
        return ans;
    }

    /**
     * 不需要辅助数组
     * @param n
     * @param start
     * @return
     */
    public int xorOperation2(int n, int start) {
        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans ^= (start + 2 * i);
        }
        return ans;
    }
}
