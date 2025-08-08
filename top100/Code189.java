package top100;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 *
 */
public class Code189 {
    public void rotate(int[] nums, int k) {
        // 直接计算i元素将要去的位置
        // 长度是N
        // n-k   0
        // n-k+1 1
        // n-k+2 2
        // 现在n位置将会去n+k % n位置
        int N = nums.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < N; i++) {
            map.put((i + k) % N, nums[i]);
        }
        for (int i = 0; i < N; i++) {
            nums[i] = map.get(i);
        }
    }
}
