package top100;

public class Code148 {
    // 排序链表

    /**
     * 思路：对链表进行归并排序
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        return process(head, null);
    }

    public ListNode process(ListNode from, ListNode to) {
        if (from == null) {
            return from;
        }
        if (from.next == to) {
            from.next = null;
            return from;
        }
        // 找到链表的中点
        ListNode slow = from;
        ListNode fast = from;
        // o o o o
        while (fast != to) {
            slow = slow.next;
            fast = fast.next;
            if (fast != to) {
                fast = fast.next;
            }
        }
        ListNode l = process(from, slow);
        ListNode r = process(slow, to);
        return merge(l, r);
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        ListNode l = head1;
        ListNode r = head2;
        while (l != null && r != null) {
            if (l.val < r.val) {
                dummy.next = l;
                l = l.next;
            } else {
                dummy.next = r;
                r = r.next;
            }
            dummy = dummy.next;
        }
        if (l != null) {
            dummy.next = l;
        }
        if (r != null) {
            dummy.next = r;
        }
        return head.next;
    }
}
