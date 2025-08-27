package top100;

/**
 * 寻找两个正序数组中位数
 * <p>
 * 严格意义上的中位数
 *
 */
public class Code004 {
    // O（m + n）做法
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        // 题目要求的是严格意义上的中位数
        int m = nums1.length;
        int n = nums2.length;
        int totalLength = m + n;
        // 如果总长度是奇数的话
        if (totalLength == (totalLength | 1)) {
            // 奇数
            // 奇数的中位数找到totalLength/2位置的数
            return findIndexNum(nums1, nums2, totalLength / 2);
        } else {
            // 偶数
            // 偶数的中位数找到(totalLength - 1)/2 和 (totalLength/2)位置的数
            return findIndexNum(nums1, nums2, (totalLength - 1) / 2) * 0.5 +
                    findIndexNum(nums1, nums2, totalLength / 2) * 0.5;
        }
    }

    public double findIndexNum(int[] nums1, int[] nums2, int target) {
        int index = 0;
        int m = nums1.length;
        int n = nums2.length;
        int ans = 0;
        int index1 = 0;
        int index2 = 0;
        while (index <= target) {
            if (index1 < m && index2 < n) {
                if (nums1[index1] < nums2[index2]) {
                    ans = nums1[index1++];
                } else {
                    ans = nums2[index2++];
                }
            } else if (index1 < m) {
                ans = nums1[index1++];
            } else {
                ans = nums2[index2++];
            }
            index++;
        }
        return ans;
    }
}
