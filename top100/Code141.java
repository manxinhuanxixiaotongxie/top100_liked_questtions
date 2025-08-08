package top100;

public class Code141 {
    // 判断一个链表是否有环
    public boolean hasCycle(ListNode head) {
        // 快慢指针
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            slow = slow.next;
            if (fast == null || fast.next == null) {
                return false; // 快指针到达链表末尾，说明没有环
            }
            fast = fast.next.next;
        }
        return true;
    }
}
