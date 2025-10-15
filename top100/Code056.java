package top100;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 */
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
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
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

    public int[][] merge2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return null;
        }
        int[][] ans = new int[intervals.length][2];
        if (intervals.length == 1) {
            ans[0][0] = intervals[0][0];
            ans[0][1] = intervals[0][1];
            return ans;
        }
        // 跟数组进行排序 根据开头位置的最小值进行升序排列
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int index = 0;
        int N = intervals.length;
        int ansIndex = 0;
        // 判断当前位置能够进行合并 如果能够进行合并的话那么将区间进行合并
        while (index < N) {
            // 进行区间合并
            int start = index;
            int min = intervals[index][0];
            int max = intervals[index][1];
            while (start < N && intervals[start][0] <= max) {
                max = Math.max(max, intervals[start][1]);
                start++;
            }
            // 合并
            ans[ansIndex][0] = min;
            ans[ansIndex++][1] = max;
            index = start;
        }
        int[][] result = new int[ansIndex][2];
        for (int i = 0; i < ansIndex; i++) {
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
        int[][] ans2 = code056.merge2(intervals);
        for (int[] interval : ans2) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }
}
