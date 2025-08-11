package top100;

public class Code024 {
    // 两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        // 上一个分组结束位置 影子节点
        ListNode pre = new ListNode(-1);
        ListNode cur = head;
        ListNode newHead = pre;
        while (cur != null && cur.next != null) {
            ListNode next = cur.next.next;
            ListNode begin = cur;
            ListNode end = cur.next;
            end.next = begin;
            begin.next = next;
            pre.next = end;
            pre = begin;
            cur = next;
        }
        return newHead.next;
    }
}
