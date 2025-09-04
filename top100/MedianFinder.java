package top100;

import java.util.PriorityQueue;

/**
 * 数据流的中位数
 *
 *
 * 思路参考灵神：
 * 中位数的定义  中位数可以将数值集合划分为相等的两部分
 * 保证left right的大小尽可能相等 规定：奇数个数时 left比right多一个
 * 保证left的所有元素都小于等于right的所有元素
 */
public class MedianFinder {
    private final PriorityQueue<Integer> left = new PriorityQueue<>((a, b) -> b - a); // 最大堆
    private final PriorityQueue<Integer> right = new PriorityQueue<>(); // 最小堆

    public void addNum(int num) {
        if (left.size() == right.size()) {
            right.offer(num);
            left.offer(right.poll());
        } else {
            left.offer(num);
            right.offer(left.poll());
        }
    }

    public double findMedian() {
        if (left.size() > right.size()) {
            return left.peek();
        }
        return (left.peek() + right.peek()) / 2.0;
    }
}
