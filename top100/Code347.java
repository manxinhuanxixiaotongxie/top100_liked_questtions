package top100;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * <p>
 * 进阶： 你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 */
public class Code347 {
    /**
     * 时间复杂度 O(N)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        // 频率出现前K高的元素
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(new int[]{entry.getKey(), entry.getValue()});
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll()[0];
        }
        return res;
    }

    /**
     * 加强堆 手写加强堆
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent2(int[] nums, int k) {
        MyHeap heap = new MyHeap(nums.length);
        for (int i = 0; i < nums.length; i++) {
            heap.add(nums[i]);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = heap.pop()[0];
        }
        return res;
    }


    class MyHeap {
        // 辅助结构
        private Map<Integer, Integer> indexMap;
        private int size;
        private int limit;
        // 堆结构 heap[0][0] = val help[0][1] = times
        private int[][] heap;

        MyHeap(int size) {
            this.size = 0;
            indexMap = new HashMap<>();
            heap = new int[size][2];
        }

        public int[] pop() {
            // 大跟堆
            int[] ans = heap[0];
            swap(heap, 0, size - 1);
            indexMap.remove(size - 1);
            size -= 1;
            heapify(0);
            return ans;
        }

        public void add(int val) {
            if (indexMap.get(val) == null) {
                this.size += 1;
                heap[size - 1][0] = val;
                heap[size - 1][1] = 1;
                indexMap.put(val, size - 1);
                heapInsert(val, size - 1);
            } else {
                int index = indexMap.get(val);
                heap[index][1] += 1;
                heapify(index);
                heapInsert(val, index);
            }
        }

        public void heapInsert(int val, int index) {
            while (heap[index][1] > heap[(index - 1) / 2][1]) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void heapify(int index) {
            int leftIndex = index * 2 + 1;
            while (leftIndex < size) {
                // 在左孩子、右孩子找到较大的下标
                int largeIndex = leftIndex + 1 < size && heap[leftIndex + 1][1] > heap[leftIndex][1] ? leftIndex + 1 : leftIndex;
                largeIndex = heap[largeIndex][1] > heap[index][1] ? largeIndex : index;
                if (largeIndex == index) {
                    break;
                }
                swap(heap, index, largeIndex);
                index = largeIndex;
                leftIndex = index * 2 + 1;
            }
        }


        private void swap(int[][] heap, int i, int j) {
            int[] temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
            indexMap.put(heap[i][0], i);
            indexMap.put(heap[j][0], j);
        }
    }

    public static void main(String[] args) {
        Code347 code347 = new Code347();
        int[] nums = {3,0,1,0};
        int k = 1;
        int[] res = code347.topKFrequent2(nums, k);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
