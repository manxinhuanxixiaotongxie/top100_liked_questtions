package newer20;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 。
 * <p>
 * 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
 * <p>
 * 返回好数对的数目。
 */
public class Code1512 {
    public int numIdenticalPairs(int[] nums) {
        // key是数组的数值 value是数值出现的次数
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            indexMap.put(nums[i], indexMap.getOrDefault(nums[i], 0) + 1);
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += indexMap.getOrDefault(nums[i], 1) - 1;
            // 结算之后 需要将当前数量减去1
            indexMap.put(nums[i], indexMap.getOrDefault(nums[i], 1) - 1);
        }
        return ans;
    }
}
