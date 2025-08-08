package top100;

import java.util.Arrays;

public class Code056 {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return null;
        }
        int[][] ans = new int[intervals.length][2];
        if (intervals.length == 1) {
            ans[0][0] = intervals[0][0];
            ans[0][1] = intervals[0][1];
            return ans;
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int end = 0;
        int index = 0;
        int min = intervals[0][0];
        int max = intervals[0][1];
        // 滑动窗口
        for (int i = 1; i <= intervals.length; i++) {
            boolean enter = false;
            while (end < intervals.length && intervals[end][0] <= max) {
                min = Math.min(min, intervals[end][0]);
                max = Math.max(max, intervals[end][1]);
                end++;
                enter = true;
            }
            // 说明没有扩大范围
            if (!enter) {
                min = intervals[end][0];
                max = intervals[end][1];
                ans[index++] = new int[]{min, max};
                i++;
                end++;
            } else {
                // 说明扩大了范围
                i = end;
                ans[index++] = new int[]{min, max};
                if (end == intervals.length) {
                    continue;
                }
                min = intervals[end][0];
                max = intervals[end][1];
            }
        }
        int[][] result = new int[index][2];
        for (int i = 0; i < index; i++) {
            result[i][0] = ans[i][0];
            result[i][1] = ans[i][1];
        }
        return result;
    }

    public static void main(String[] args) {
        Code056 code056 = new Code056();
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] ans = code056.merge(intervals);
        for (int[] interval : ans) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }
}
