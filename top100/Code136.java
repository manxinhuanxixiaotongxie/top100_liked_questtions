package top100;

public class Code136 {
    /**
     * 利用异或的特性 相同为0 不同为1
     *
     * 出现两次的数  异或的结果为0
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for(int i = 0;i <nums.length;i++) {
            ans ^=nums[i];
        }
        return ans;
    }
}
