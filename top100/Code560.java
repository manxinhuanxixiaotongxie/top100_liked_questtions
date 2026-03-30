package top100;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * <p>
 * 子数组是数组中元素的连续非空序列。
 */
public class Code560 {

    /**
     * 子数组的问题
     * 优先考虑以i开头 或者以i结尾的子数组
     * 本题采用以i结尾的子数组
     * 1.先求前缀和
     * 2. 如果以i结尾的子数组的和为k 此时sum[i]为一个数
     * 等效于求以i结尾之前的有多少个子数组的前缀和在sum[i] -k
     *
     * 变形题
     * 1.改成计算元素和等于 k 的最短子数组，要怎么做？
     * 2.改成计算元素和等于 k 的最长子数组，要怎么做？
     * 3.改成计算元素和等于 k 的所有子数组的长度之和，要怎么做？
     * 4.改成元素和至多为 k，要怎么做？见 363. 矩形区域不超过 K 的最大数值和。
     * 5.改成计算元素和为奇数的子数组个数，要怎么做？     * 1.改成计算元素和等于 k 的最短子数组，要怎么做？
     * 2.改成计算元素和等于 k 的最长子数组，要怎么做？
     * 3.改成计算元素和等于 k 的所有子数组的长度之和，要怎么做？
     * 4.改成元素和至多为 k，要怎么做？见 363. 矩形区域不超过 K 的最大数值和。
     * 5.改成计算元素和为奇数的子数组个数，要怎么做？
     *
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        // 必须以i位置结尾的字数组
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = nums[i] + sum[i - 1];
        }
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            ans += map.getOrDefault(sum[i] - k, 0);
            map.put(sum[i], map.getOrDefault(sum[i], 0) + 1);
        }
        return ans;
    }

    /**
     * 一次遍历
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum2(int[] nums, int k) {
        // 必须以i位置结尾的字数组
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            ans += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }

    /**
     * 变形1：计算元素和等于 k 的最短子数组长度
     * 思路：前缀和 + HashMap 记录每个前缀和最后一次出现的下标
     * 要找最短 → 就要让左端点 j 尽可能大 → 记录前缀和的最晚下标
     * prefix[i] - prefix[j] = k → prefix[j] = prefix[i] - k
     * 长度 = i - j，最小化 → j 越大越好 → map 每次都覆盖更新（保留最新下标）
     */
    public int shortestSubarraySum(int[] nums, int k) {
        int ans = Integer.MAX_VALUE;
        // map: 前缀和 -> 最后一次出现的下标（0 表示空前缀）
        Map<Integer, Integer> lastIndex = new HashMap<>();
        lastIndex.put(0, 0);
        int prefixSum = 0;
        for (int i = 1; i <= nums.length; i++) {
            prefixSum += nums[i - 1];
            if (lastIndex.containsKey(prefixSum - k)) {
                ans = Math.min(ans, i - lastIndex.get(prefixSum - k));
            }
            // 始终用最新下标覆盖，保证 j 尽可能大（子数组尽可能短）
            lastIndex.put(prefixSum, i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * 变形2：计算元素和等于 k 的最长子数组长度
     * 思路：前缀和 + HashMap 记录每个前缀和第一次出现的下标
     * 要找最长 → 就要让左端点 j 尽可能小 → 记录前缀和的最早下标
     * prefix[i] - prefix[j] = k → 长度 = i - j，最大化 → j 越小越好
     * map 只在不存在时才写入（putIfAbsent），保留最早下标
     */
    public int longestSubarraySum(int[] nums, int k) {
        int ans = 0;
        // map: 前缀和 -> 第一次出现的下标（0 表示空前缀）
        Map<Integer, Integer> firstIndex = new HashMap<>();
        firstIndex.put(0, 0);
        int prefixSum = 0;
        for (int i = 1; i <= nums.length; i++) {
            prefixSum += nums[i - 1];
            if (firstIndex.containsKey(prefixSum - k)) {
                ans = Math.max(ans, i - firstIndex.get(prefixSum - k));
            }
            // 只在第一次出现时写入，保证 j 尽可能小（子数组尽可能长）
            firstIndex.putIfAbsent(prefixSum, i);
        }
        return ans;
    }

    /**
     * 变形3：计算元素和等于 k 的所有子数组的长度之和
     * 思路：前缀和 + HashMap 记录每个前缀和出现的次数和下标之和
     * 满足 prefix[j] = prefix[i] - k 的所有 j 对应长度之和
     * = Σ(i - j) = count * i - sumJ
     * map 存储：前缀和 -> [出现次数, 所有出现下标之和]
     */
    public long sumOfLengthsSubarraySum(int[] nums, int k) {
        long ans = 0;
        // map: 前缀和 -> [出现次数, 所有下标之和]
        Map<Integer, long[]> map = new HashMap<>();
        map.put(0, new long[]{1, 0}); // 空前缀，下标为 0
        int prefixSum = 0;
        for (int i = 1; i <= nums.length; i++) {
            prefixSum += nums[i - 1];
            if (map.containsKey(prefixSum - k)) {
                long[] val = map.get(prefixSum - k);
                long count = val[0]; // 满足条件的前缀和出现了多少次
                long sumJ = val[1];  // 这些下标的总和
                // 所有满足条件的 j，贡献长度 = count*i - sumJ
                ans += count * i - sumJ;
            }
            long[] cur = map.getOrDefault(prefixSum, new long[]{0, 0});
            cur[0]++;    // 出现次数 +1
            cur[1] += i; // 累加当前下标
            map.put(prefixSum, cur);
        }
        return ans;
    }

    /**
     * 变形4：元素和至多为 k 的最大子数组和（参考 LC 363）
     * 思路：前缀和 + TreeSet（有序集合）
     * 子数组和 = prefix[i] - prefix[j]，要求 ≤ k 且尽量大
     * 即 prefix[j] ≥ prefix[i] - k 且尽量小 → ceiling(prefix[i] - k)
     * 用 TreeSet 维护已出现的前缀和，ceiling 查找满足条件的最小 prefix[j]
     */
    public int maxSumAtMostK(int[] nums, int k) {
        int ans = Integer.MIN_VALUE;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0); // 空前缀
        int prefixSum = 0;
        for (int num : nums) {
            prefixSum += num;
            // 找最小的 prefix[j] >= prefixSum - k，使子数组和尽量大且 ≤ k
            Integer ceiling = set.ceiling(prefixSum - k);
            if (ceiling != null) {
                ans = Math.max(ans, prefixSum - ceiling);
            }
            set.add(prefixSum);
        }
        return ans;
    }

    /**
     * 变形5：计算元素和为奇数的子数组个数
     * 思路：子数组和 = prefix[i] - prefix[j] 为奇数
     *       ↔ prefix[i] 和 prefix[j] 奇偶性不同
     * 只需统计当前前缀和为偶数时，之前有多少奇数前缀和（含空前缀=0，偶数）
     *           当前前缀和为奇数时，之前有多少偶数前缀和
     * 用两个计数器 evenCount / oddCount 替代 map，O(n) 时间 O(1) 空间
     */
    public long countOddSubarraySum(int[] nums) {
        long ans = 0;
        int evenCount = 1; // 空前缀 prefix[0]=0 是偶数
        int oddCount = 0;
        int prefixSum = 0;
        for (int num : nums) {
            prefixSum += num;
            if (prefixSum % 2 == 0) {
                // 当前前缀和为偶数，需要奇数前缀和配对
                ans += oddCount;
                evenCount++;
            } else {
                // 当前前缀和为奇数，需要偶数前缀和配对
                ans += evenCount;
                oddCount++;
            }
        }
        return ans;
    }
}
