package top100;

import java.util.PriorityQueue;

/**
 * 数组中的第K个最大元素
 * 你必须设计并实现时间复杂度为 O(n) 的算法来解决此问题。
 */
public class Code215 {

    /**
     * 第一种思路
     * 维护一个大小为k的小根堆
     * 堆的含义是 前k大的数
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        // 默认小根堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int N = nums.length;
        for (int i = 0; i < Math.min(N, k); i++) {
            pq.add(nums[i]);
        }
        if (N <= k) {
            return pq.peek();
        }
        for (int i = k; i < N; i++) {
            if (pq.peek() < nums[i]) {
                pq.poll();
                pq.add(nums[i]);
            }
        }
        return pq.peek();
    }

    /**
     * 第二种思路：
     * 改写快排
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return process(nums, 0, nums.length - 1, k - 1);
    }


    public int process(int[] nums, int l, int r, int k) {
        if (l >= r) {
            return nums[l];
        }
        int[] range = partition(nums, l, r);
        if (k >= range[0] && k <= range[1]) {
            return nums[range[0]];
        } else if (k < range[0]) {
            return process(nums, l, range[0] - 1, k);
        } else {
            return process(nums, range[1] + 1, r, k);
        }
    }


    public int[] partition(int[] nums, int l, int r) {
        // 将数组划分成左边大、右边小的结构
        int left = l - 1;
        int right = r + 1;
        int i = l;
        int index = l + (int) (Math.random() * (r - l + 1));
        int val = nums[index];
        while (i < right) {
            if (nums[i] == val) {
                i++;
            } else if (nums[i] < val) {
                swap(nums, i, --right);
            } else {
                swap(nums, i++, ++left);
            }
        }
        return new int[]{left + 1, right - 1};
    }


    /**
     * 这个方法是错的 为什么？
     * 因为没有固定值 在后续的交换过程中可能会导致nums[index]发生变化
     * 必须 要先固定
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    public int[] partition2(int[] nums, int l, int r) {
        // 拆分数组
        int left = l - 1;
        int right = r + 1;
        int i = l;
        // 随机划分
        int index = l + (int) (Math.random() * (r - l + 1));
        while (i < right) {
            if (nums[i] > nums[index]) {
                swap(nums, i++, ++left);
            } else if (nums[i] < nums[index]) {
                swap(nums, i, --right);
            } else {
                i++;
            }
        }
        return new int[]{left + 1, right - 1};
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * 第三种思路
     * bfprt
     * bfprt的核心就是为了解决快排中划分不均匀的问题 找到的那个数是随机的 可能会出现分配不均衡的情况
     * 因此bfprt就是为了找到那个天选之子
     * 1.在l到r范围上进行分组 每五个数为一组 不足五个数的一组也算一组
     * 2.对每组进行排序 找到每组的中位数
     * 3.每组的中位数组成一个新的数组 递归调用bfprt 找到中位数的中位数
     * 4.用中位数的中位数作为划分值 对数组进行划分
     * 5.判断k落在哪个范围内 继续递归
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest3(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return bfprt(nums, 0, nums.length - 1, k - 1);
    }

    public int bfprt(int[] nums, int l, int r, int k) {
        if (l == r) {
            return nums[l];
        }
        int pivot = medianOfMedians(nums, l, r);
        int[] range = partition(nums, l, r, pivot);
        if (k >= range[0] && k <= range[1]) {
            return nums[k];
        } else if (k < range[0]) {
            return bfprt(nums, l, range[0] - 1, k);
        } else {
            return bfprt(nums, range[1] + 1, r, k);
        }
    }

    public int[] partition(int[] nums, int l, int r, int pivot) {
        int left = l - 1;
        int right = r + 1;
        int i = l;
        while (i < right) {
            if (nums[i] == pivot) {
                i++;
            } else if (nums[i] < pivot) {
                swap(nums, i, --right);
            } else {
                swap(nums, i++, ++left);
            }
        }
        return new int[]{left + 1, right - 1};
    }

    public int medianOfMedians(int[] nums, int l, int r) {
        int size = r - l + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int numMedians = size / 5 + offset;
        int[] medians = new int[numMedians];
        for (int i = 0; i < numMedians; i++) {
            int subL = l + i * 5;
            int subR = Math.min(r, subL + 4);
            medians[i] = getMedian(nums, subL, subR);
        }
        return bfprt(medians, 0, medians.length - 1, medians.length / 2);
    }

    public int getMedian(int[] nums, int l, int r) {
        insertionSort(nums, l, r);
        return nums[l + (r - l) / 2];
    }

    public void insertionSort(int[] nums, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            int key = nums[i];
            int j = i - 1;
            while (j >= l && nums[j] > key) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;
        }
    }


    public static void main(String[] args) {
        Code215 code215 = new Code215();
        int[] arr = {3, 2, 1, 5, 6, 4};
        int k = 2;
        int res = code215.findKthLargest2(arr, k);
        System.out.println(res);
    }
}
